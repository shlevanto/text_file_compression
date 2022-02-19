# Weekly report 5

## My activities this week
- LZSS
- BWT in chunks can now handle bigger strings, file handling needs to be updated.
- Updating file operations.
- Peer review.

## Progress
- LZSS works on a conceptual level / with strings. 
- The chunked BWT can now be saved to file, but there are some issues.

## Lessons learned
- Filesizes can get surprisingly large...

## Issues, unclarities and questions
- LZSS sliding buffer window.
- Why is the filesize for the chunked BWT + RLE so big? It should compress the large DNA nucleotide file but it doesn't...
- I can't seem to get compression rate below 85% for LZSS even if I pack the token into 6 bytes. Maybe it should be 3, but then we need the sliding window.

## Plan for next steps
- LZSS needs a sliding window for the search buffer in order to make it more effective. If the offset gets too long, it wont tokenize. 
- The encodings should be broken up into smaller methods. For example LZSS could use tokenize and deTokenize. Token could maybe be a class?

## Time used
14