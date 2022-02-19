## Project structure
Each compression algorithm is its own class with encode and decode methods. The compression will work on UTF-8 encoded text. File interactions are handled with the FileIO class. Pair and SuffixArray are helper classes that implement data structures needed by the compression algorithms.

## Implemented time and space complexities (big-O complexity analysis of (pseudo)code)
Pseudocode will come later once I have the code finalized.

### RLE
Time complexity O(n), space complexity O(n).

function encode(string)
    chars = stringAsCharArray
    counts = []
    for (char in chars) 
        if char == nextChar
            counts[charIndex]++

function decode(chars, counts)
    s = ""
    for (n in counts)
        s += char[nIndex]

### BWT
Time complexity O(n log n) due to the sorting of the suffix array in encoding and sorting of the encoded string in the decoding method. Space complexity might be n^2, because the suffix array has rows equal to the strings length?

function encode(string)
    create(suffixArray)
    sort(suffixArray)
    encoded getLastColumn(suffixArray)

function decode(string)
    chars = stringAsCharArray
    sort(chars)
    LFMap(chars)

### LZSS
For the LZSS algorithm, I drew inspiration from [The Hitchhiker's Guide to Compression](https://go-compression.github.io/algorithms/lzss/).

## Comparative performance

## Possible flaws and improvements
At the moment I have issues with file size as BWT handles the encoding using the whole content of the file as one string. I think I need to break it into chunks to be able to handle bigger files.

## Sources
[LZSS](https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Storer%E2%80%93Szymanski)

[RLE](https://en.wikipedia.org/wiki/Run-length_encoding)

[BWT](https://en.wikipedia.org/wiki/Burrows%E2%80%93Wheeler_transform) and [original article](https://www.hpl.hp.com/techreports/Compaq-DEC/SRC-RR-124.html)

[LF Mapping](https://web.stanford.edu/class/cs262/archives/notes/lecture4.pdf) See page 12 and onwards for the idea of the mapping as an effective means to reverse the BWT encoding.

And it turned out that there exists a concept of Run Length Encoded Burrows-Wheeler transformation [article](https://drops.dagstuhl.de/opus/volltexte/2017/7321/pdf/LIPIcs-CPM-2017-17.pdf)

