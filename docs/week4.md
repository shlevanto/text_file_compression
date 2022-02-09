# Weekly report 4

## My activities this week
- File handling to be done with bytes / byte arrays.

## Progress
- Changed RLE to work on strings and Pair<char[], int[]> objects. All i/o operations will be moved to FileIO class.
- Implemented a working solution for writing and reading encoded files as bytes / byte arrays. I did not use ByteArrayStreams, because I need to distinguish between the header and the rest of the file. The header tells us how much of the byte array is the RLE counts and then the rest is char.

## Lessons learned
- Java byte values are -128 - 127 so encoding UTF-8 chracters (with values 0 - 255) required some extra moves. Very useful exercise.
- Due to byte size the max value that I can use for run length is 127. I tried using ByteOutPutStream, but that only made the files larger so I will limit run length to 127 in the RLE class.

## Issues, unclarities and questions
Now the max run length is 127 because I use one byte to store the run length. Most runs seem to be fairly short even in bigger files, so I think it would not be wise to store all run lengths in two or more bytes... Is it a good (enough) solution to just limit run length to 127 and start a new run if the limit is hit?

## Plan for next steps
I think it is now time to tackle LZSS. I will leave UI for last, because it is goint to be very simple command line based.

## Time used
8