# Weekly report 6

## My activities this week
- Working on LZSS.
- Arranging and project structure. 
- Built Command line interface.
- Peer review.
- BWT + RLE file operations witch chunked encoding.
- Updated unit tests
- Appeased checkstyle

## Progress
- LZSS works with byte arrays and files.
- Project structure arranged into packages.
- LZSS sliding window works.
- Simple but working command line interface implemented with picocli -library.
- The application now more or less works, the compressions still have some issues.

## Lessons learned
- In some texts, there are left and right quotation marks. There is no 8-bit representation of these so my encoding only worked if the character can be represented in 8 bit format. I swithced from encoding char [] to encoding byte [] and this fixed the issue.
- I learned you can do slicing of arrays in Java with Arrays.copyOfRange. This made coding the project a lot easier :)
- The LZSS scans for a match beginning from the buffer start. If a short match is found, it is not tokenized and the search is aborted. I altered it to continue searching until finding somethin tokenizable or reaching end of buffer.

## Issues, unclarities and questions
- The token size for LZSS is quite big (6 bytes). I tried shaving it to 5, but couldn't get it to work. At the moment I can reach compression to about 82% of original with small files. I chose to have a working algorithm instead of trying to maximize compression. I will look into compression rate during the final week.
- LZSS doesn't compress big (3-5 MB) files. I can only get a compression to 99%... No idea why.
- Because of size complexity, BWT encoding needs to have limited size input. I tried diciding the input into chunks and encoding / decoding them but there is still a bug in the syste, that I have to work out. So BWT and RLE work, but the chunk system has some issues.

## Plan for next steps
- Increasing compression rate.
- Testing and documentation.

## Time used
22