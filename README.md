![GitHub Actions](https://github.com/shlevanto/tiralabra-2022/actions/workflows/main.yml/badge.svg)
[![codecov](https://codecov.io/gh/shlevanto/tiralabra-2022/branch/main/graph/badge.svg?token=0EE9F994BB)](https://codecov.io/gh/shlevanto/tiralabra-2022)

# tiralabra-2022
Lab project concerning datastructures and algorithms, University of Helsinki. Course code TKT20010, spring semester 2022. 

## Study program
Tietojenkäsittelytieteen kandidaattiohjelma. 

## Backlog
[Backlog](https://github.com/shlevanto/tiralabra-2022/projects/1)

## Documentation
[Specification](/docs/specification.md)

[Implementation](/docs/implementation.md)

[Test documentation](/docs/test_documentation.md)

[Weekly progress](/docs/weekly/)

## User guide
The project has a simple command line interface. 
```
Usage: <main class> [-dhv] [--performance] [--showbwt] -m=<method> <filepath>
      <filepath>          The file to compress.
  -d, --decompress        decompress the given file using chosen method
  -h, --help              displays a help message
  -m, --method=<method>   compression method: 'l' for LZSS or 'b' for BWT + RLE
      --performance       run performance tests
      --showbwt           displays the BWT transformation of the input
  -v, --verify            decompresses the file and verifies that the
                            decompression matches original content                       
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
