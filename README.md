![GitHub Actions](https://github.com/shlevanto/tiralabra-2022/actions/workflows/main.yml/badge.svg)
[![codecov](https://codecov.io/gh/shlevanto/tiralabra-2022/branch/main/graph/badge.svg?token=0EE9F994BB)](https://codecov.io/gh/shlevanto/tiralabra-2022)

# tiralabra-2022
Lab project concerning datastructures and algorithms, University of Helsinki. Course code TKT20010, spring semester 2022. 

## Study program
Tietojenkäsittelytieteen kandidaattiohjelma. 

## Backlog
[Backlog](https://github.com/shlevanto/tiralabra-2022/projects/1)

## Description
This project concerns lossless compression methods for text data. I will be comparing two different compression methods on a variety of text files with different properties (ie. actual readable text, generated text content and compression benchmark data). 

## Languages used
The project will be done in Java. I am open for peer reviewing projects coded with both Java and Python. All documentation will be in English but I am happy to both give and receive feedback and peer reviews in Finnish, English and Swedish.

## Algorithms used
I will be implementing the Lempel–Ziv–Storer–Szymanski (LZSS) algorithm and Run Length Encoding (RLE) algorithm enhanced with a Burrows–Wheeler Transform (BWT) algorithm. I chose LZSS because it is only a slight optimization of the original Lempel-Ziv algorithm and I would like to get to know the classics in this field. As a comparision I chose RLE because it is not very effective as such and I want to see how much BWT can enhance its performance.

## Specification of the program
The program will work from the command line. The user will input a path. The program will compress the file with each of the algorithms and report the compression rate and time used. The program will also decompress the file and check that the compression was flawless. The program will include a demo mode that goes through a predefined set of text files and generates a report of the compression performance. The predefined set of files can be configured by the user.

## Time and space complexity
The time complexity of each of the algorithms used is expected to be O(n). For BTW this implies the use of a suffix array data structure which I will implement for the project.

## Weekly progress reports
[Week 1](/docs/week1.md)

## References
[LZSS](https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Storer%E2%80%93Szymanski)

[RLE](https://en.wikipedia.org/wiki/Run-length_encoding)

[BWT](https://en.wikipedia.org/wiki/Burrows%E2%80%93Wheeler_transform)

And it turned out that there exists a concept of Run Length Encoded Burrows-Wheeler transformation [article](https://drops.dagstuhl.de/opus/volltexte/2017/7321/pdf/LIPIcs-CPM-2017-17.pdf)

