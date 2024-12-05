# Burrows-Wheeler Compression Project

This Java project implements a compression algorithm using Burrows-Wheeler Transform (BWT), Move-To-Front (MTF) encoding, and Huffman encoding. The project includes a command-line interface (CLI) application that can compress and extract text files.

## Features

- **Burrows-Wheeler Transform (BWT)**: A data transformation algorithm that rearranges a string into runs of similar characters, which is useful for compression.
- **Move-To-Front (MTF) Encoding**: An encoding technique that improves the efficiency of Huffman encoding by reducing the entropy of the input data.
- **Huffman Encoding**: A popular compression algorithm that uses variable-length codes to represent characters based on their frequencies.

## CLI Application

The CLI application allows users to compress and extract text files using the implemented algorithms.

### Usage

To use the CLI application, you need to specify the action (compress or extract), the input file, and the output file.

#### Compress a File

```sh
java -jar BurrowsWheeler.jar -compress -f <inputFile> -o <outputFile>
```

#### Extract a File

```sh
java -jar BurrowsWheeler.jar -extract -f <inputFile> -o <outputFile>
```

### Options

- `-compress`: Compress the input file.
- `-extract`: Extract the input file.
- `-f <inputFile>`: Specify the input file path.
- `-o <outputFile>`: Specify the output file path.
- `-help`: Show the help message.

## Project Structure

- `src/main/java/burrowswheeler/benchmark`: Contains benchmarking classes for performance evaluation.
- `src/main/java/burrowswheeler/compressor`: Contains the main compressor class that integrates BWT, MTF, and Huffman encoding.
- `src/main/java/burrowswheeler/main`: Contains the main class for the CLI application.
- `src/main/java/burrowswheeler/utils`: Contains utility classes for BWT, MTF, and Huffman encoding.
- `src/test/java/burrowswheeler/utils`: Contains integration tests for the utility classes.

## License

This project is licensed under the MIT License.
