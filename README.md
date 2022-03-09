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
Usage: <main class> [-dhv] [--showbwt] -m=<method> <filepath>
      <filepath>          The file to compress.
  -d, --decompress        decompress the given file using chosen method
  -h, --help              displays a help message
  -m, --method=<method>   compression method: 'l' for LZSS or 'b' for BWT + RLE
      --showbwt           displays the BWT transformation of the input
  -v, --verify            verifies that the decompression matches original
                            content
```
You can run the project using gradle
```
./gradlew <filename> -m <l or b>
```
or build the project using
```
./gradlew fatJar
```
and run
```
java -jar tirautin <filename> -m <l or b> <additional flags>
```
There is also a ready made jar -file in releases. Please notice that the ```config.properties``` -file included in the releases needs to be in the same folder as the jar -file that is being run.

I've included two files: poem.txt is a poem in English and nucleotide-sample.txt contains some DNA sequences.



