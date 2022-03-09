## Project structure
Each compression algorithm is its own class with encode and decode methods. The compression will work on UTF-8 encoded text. File interactions are handled with the FileIO class. Pair and SuffixArray are helper classes that implement data structures needed by the compression algorithms.

## Implemented time and space complexities (big-O complexity analysis of (pseudo)code)
Pseudocode will come later once I have the code finalized.

### RLE
Time complexity O(n), space complexity O(n). The run is encoded as a 3 byte token (marker, run length, character) and only runs that are longer than 3 are encoded.

```
function encode(string)
    chars = stringAsCharArray
    counts = []
    for (char in chars) 
        if char == nextChar
            counts[charIndex]++
    for (char in chars, count in counts)
        if (count > 3)
            encoded.add(marker, count, char)
        else 
            encoded.add(char)
    return encoded

function decode(encoded)
    s = ""
    for (i in encoded.length)
        if encoded[i] = marker
            s += encoded[i+1] * encoded[i+2]
        else
            s += encoded[i]
        
```

### BWT
Time complexity O(n log n) due to the sorting of the suffix array in encoding and sorting of the encoded string in the decoding method. Space complexity is case n^2, because the suffix array has rows equal to the strings length. When handling larger files, the string is divided into chunks and each chunk is then separately transformed. These chunks are parsed back to for a string, which is then compressed using RLE. The restoration is much more efficient than transformation. The LF-mapping is O(n log n) for time because it includes a sort and O(n) in space complexity. 

```
function transform(string)
    create(suffixArray)
    sort(suffixArray)
    return getLastColumn(suffixArray)

function restore(string)
    chars = stringAsCharArray
    sort(chars)
    LFMap(chars)
```

### LZSS
For the LZSS algorithm, I drew inspiration from [The Hitchhiker's Guide to Compression](https://go-compression.github.io/algorithms/lzss/). At each character LZSS scans the text that has been encoded so far and looks for a matching character. If it finds one, it matches the next chraracter and counts how long a string it can match. This match is then encoded to a token that is the encoding. The length of the token is 5 bytes so LZSS only tokenizes matches of length 5 or greater. The space complexity is O(n) because the string to be encoded and it's duplicate in the buffer is used. Time complexity is O(n log n) as for each character, the text so far is searched. This however results in very long performance times so a sliding buffer is used. This means that the algorithm will start to look for matches for at most n characters backwards where n is the buffer size. LZSS decoding time complexity is O(log n) because it only goes back to search for strings when it encounters tokens, not for every byte in the string.

```
function encode(string)
    buffer = []
    for (char in string)
        if string[i] in buffer
            starting from index of char in buffer
                check length of matching string
            if match.length > 5
                make token
                add tokenized chars to buffer
            else 
                look for match from index of char in buffer + 1
            add char to buffer

function decode(encoded)
    s = ""
    for (i in encoded.length)
        if i = token
            build string with token.length from i - token.offset 
            s += string
        else
            s += encoded[i]
```


## Possible flaws and improvements
The compresion rate is around 60-70 for LZSS so it is not as good as it should be. BWT + RLE performs very varying on natural text. 

For BWT the suffix form is created with a naive methdo where all suffixes are put into an array and this array is sorted. This has a time complexity of =(n log n) and space complexity of O(n^2). So not very effectvie. The suffix array could also be built in linear time and less space complexity, but there wasn't enough time for this. The method is explained [here](https://www.cs.helsinki.fi/u/tpkarkka/opetus/10s/spa/lecture11.pdf). 

For RLE the run length is stored in one byte and can be max 127. This could easily be doubled to 255 because this is the max size of an unsigned integer that can be stored in one byte.

## Sources
[LZSS](https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Storer%E2%80%93Szymanski)

[RLE](https://en.wikipedia.org/wiki/Run-length_encoding)

[BWT](https://en.wikipedia.org/wiki/Burrows%E2%80%93Wheeler_transform) and [original article](https://www.hpl.hp.com/techreports/Compaq-DEC/SRC-RR-124.html)

[LF Mapping](https://web.stanford.edu/class/cs262/archives/notes/lecture4.pdf) See page 12 and onwards for the idea of the mapping as an effective means to reverse the BWT encoding.

And it turned out that there exists a concept of Run Length Encoded Burrows-Wheeler transformation [article](https://drops.dagstuhl.de/opus/volltexte/2017/7321/pdf/LIPIcs-CPM-2017-17.pdf)

