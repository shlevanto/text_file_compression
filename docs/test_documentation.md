## Test documentation

### Unit test coverage
The unit test coverage is reported in CodeCov [![codecov](https://codecov.io/gh/shlevanto/tiralabra-2022/branch/main/graph/badge.svg?token=0EE9F994BB)](https://codecov.io/gh/shlevanto/tiralabra-2022)

### What has been tested
Unit tests will cover basic functionalities
- encoding results in a byte array with length > 0
- the encoded file is not larger than the original
- a string is encoded and decoded, the decoded matches the original

FileIO has tests for succesfully reading and writing files and throwing exceptions when this fails.

Main and UI are excluded from test coverage. I have not written tests for the Service class as all it does is run the different compressors.

### Performance tests
For performance testing different size samples of the same text were used. The text used was the [King James Bible](https://www.gutenberg.org/cache/epub/10/pg10.txt) from the Project Gutenberg site. The results are shown in the table and graphs below.

### Test repeatability
Performance tests can be run using any text file in the root folder of the project or in the folder the jar -file is run from. The performance tests are run by adding the flag ```--performance``` when running the compression program.
