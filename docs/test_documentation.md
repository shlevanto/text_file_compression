## Test documentation

### Unit test coverage
The unit test coverage is reported in CodeCov [![codecov](https://codecov.io/gh/shlevanto/tiralabra-2022/branch/main/graph/badge.svg?token=0EE9F994BB)](https://codecov.io/gh/shlevanto/tiralabra-2022)

### What has been tested
Unit tests will cover basic functionalities
- encoding results in a byte array with length > 0
- the encoded file is not larger than the original
- a string is encoded and decoded, the decoded matches the original

FileIO has tests for succesfully reading and writing files and throwing exceptions when this fails.

### Input used
As BWT + RLE is best suited for sequences (bits, bytes, DNA sequences etc.), I will compare this kind of data with natural text for the BWT + RLE vs. LZSS compressions. Natural language vs. random content will be compared.

### Test repeatability
All content used for the test runs will be provided in this repository. I will also build an option into the UI to run these tests. 
