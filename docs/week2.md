# Weekly report 1

## My activities this week

## Progress

## Lessons learned
The instructions for RLE seem simple, just parse a string with count and character. AABBA becomes 2A2B1A. However I expect the text data to contain numbers in the text and have to take into account encoding for example 9113 which would end up as a string 192113. There is no way of knowing if this decodes to 9112 or 222222222222222222233333333333. So I decide to do two strings. One is the counts, separated by commas and the other one is the characters. The program will handle these as arrays. This is obviously very inneffective if there are no long runs and will end up increasing file size. I hope that BWT will turn this around. 

## Issues, unclarities and questions

## Plan for next steps

## Time used
2 