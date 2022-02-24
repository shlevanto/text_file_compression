# Weekly report 6

## My activities this week
- Working on LZSS and project structure. 
- Built Command line interface.
## Progress
- LZSS works with byte arrays and files.
- Changed LZSS so that String is converted to byte[], not char[] before encoding as some UTF-8 characters don't fit into a single byte.
- Project structure arranged into packages.
- LZSS sliding window works.
- Simple but working command line interface implemented with picocli -library.

## Lessons learned
- In some texts, there are left and right quotation marks. There is no 8-bit representation of these so my encoding only works if the character can be represented in 8 bit format.
- I learned you can do slicing of arrays in Java with Arrays.copyOfRange. This made coding the project a lot easier :)

## Issues, unclarities and questions
- Issues with quotation marks if they are not ascii = 22. I'm sticking with one character per byte encoding, so I have to find some solution for this.
- Now the LZSS scans for a match beginning from the buffer start. If a short match is found, it is not tokenized and the search is aborted. I have to find a way for it to continue searching until finding somethin tokenizable or reaching end of buffer.

## Plan for next steps

## Time used
14