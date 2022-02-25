# Weekly report 6

## My activities this week
- Working on LZSS.
- Arranging and project structure. 
- Built Command line interface.
- Peer review.
- BWT + RLE file operations witch chunked encoding.

## Progress
- LZSS works with byte arrays and files.
- Project structure arranged into packages.
- LZSS sliding window works.
- Simple but working command line interface implemented with picocli -library.

## Lessons learned
- In some texts, there are left and right quotation marks. There is no 8-bit representation of these so my encoding only works if the character can be represented in 8 bit format.
- I learned you can do slicing of arrays in Java with Arrays.copyOfRange. This made coding the project a lot easier :)
- The LZSS scans for a match beginning from the buffer start. If a short match is found, it is not tokenized and the search is aborted. I altered it to continue searching until finding somethin tokenizable or reaching end of buffer.

## Issues, unclarities and questions
- The token size for LZSS is quite big (6 bytes). I tried shaving it to 5, but couldn't get it to work. At the moment I can reach compression to about 75% of original. I chose to have a working algorithm instead of trying to maximize compression. I will look into this during the final week if I get everything else up and running.

## Plan for next steps
- Testing and documentation.

## Time used
18