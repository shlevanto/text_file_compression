![GitHub Actions](https://github.com/shlevanto/tiralabra-2022/actions/workflows/main.yml/badge.svg)
[![codecov](https://codecov.io/gh/shlevanto/tiralabra-2022/branch/main/graph/badge.svg?token=0EE9F994BB)](https://codecov.io/gh/shlevanto/tiralabra-2022)

# tiralabra-2022
Lab project concerning datastructures and algorithms, University of Helsinki. Course code TKT20010, spring semester 2022. 

## Study program
Tietojenkäsittelytieteen kandidaattiohjelma. 

## Backlog
[Backlog](https://github.com/shlevanto/tiralabra-2022/projects/1)

## Specification
[Week 1](/docs/specification.md)

## Weekly progress reports
[Week 1](/docs/week1.md)

[Week 2](/docs/week2.md)

[Week 3](/docs/week3.md)

[Week 4](/docs/week4.md)

[Week 5](/docs/week5.md)

[Week 6](/docs/week6.md)

## Test documentation
[Week 3-4](/docs/test_documentation.md)

## Implementation document
[Week 4](/docs/implementation.md)

## User guide
The project has a simple command line interface. 
```
Usage: <main class> [-hv] [--verbose] -m=<method> <filepath>
      <filepath>          The file to compress.
  -h, --help              displays a help message
  -m, --method=<method>   compression method: 'l' for LZSS or 'b' for BWT + RLE
  -v, --verify            verifies that the decompression matches original
                            content
      --verbose           prints the BWT transformed string
```
I don't have a build yet, but you can run it with gradle using
```
./gradlew <filename> -m <l or b>
```

I've included two files: poem.txt is a poem in English and nucleotide-sample.txt contains some DNA sequences. The BWT + RLE encoding doesn't compress natural language, but it is effective with content that contains a limited amount of characters. 



