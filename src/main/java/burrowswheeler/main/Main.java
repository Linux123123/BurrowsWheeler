package burrowswheeler.main;

import burrowswheeler.compressor.Compressor;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Main {
    public static void main(String[] args) {
        // Create the command line parser
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();

        // Define the options for the command
        Option compress = new Option("compress", false, "Compress the input file");
        Option extract = new Option("extract", false, "Extract the input file");
        Option inputFile = Option.builder("f")
                .argName("inputFile")
                .hasArg()
                .desc("Input file path")
                .required(true)
                .build();
        Option outputFile = Option.builder("o")
                .argName("outputFile")
                .hasArg()
                .desc("Output file path")
                .required(true)
                .build();
        Option help = new Option("help", false, "Show help message");

        // Add options to the Options object
        options.addOption(compress);
        options.addOption(extract);
        options.addOption(inputFile);
        options.addOption(outputFile);
        options.addOption(help);

        try {
            // Parse the command line arguments
            CommandLine cmd = parser.parse(options, args);

            // Display help if -help is provided
            if (cmd.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("BurrowsWheeler.jar", options);
                return; // Exit after showing help
            }

            // Get the input file path from the command line
            String inputPath = cmd.getOptionValue("f");

            // Validate the input file path
            File inputFileObj = new File(inputPath);
            if (!inputFileObj.exists()) {
                System.out.println("Error: The file does not exist at the provided path: " + inputPath);
                return; // Exit after displaying error
            }
            if (!inputFileObj.isFile()) {
                System.out.println("Error: The path is not a valid file: " + inputPath);
                return; // Exit after displaying error
            }

            // Get the output file path from the command line
            String outputPath = cmd.getOptionValue("o");
            File outputFileObj = new File(outputPath);


            // Check which action is requested: compress or extract
            if (cmd.hasOption("compress")) {
                System.out.println("Compressing: " + inputPath + " to " + outputPath);
                String inputData = Files.readString(inputFileObj.toPath());
                Files.write(outputFileObj.toPath(), Compressor.encode(inputData));
                System.out.println("Compression complete.");
            } else if (cmd.hasOption("extract")) {
                System.out.println("Extracting: " + inputPath + " to " + outputPath);
                byte[] inputData = Files.readAllBytes(inputFileObj.toPath());
                Files.writeString(outputFileObj.toPath(), Compressor.decode(inputData));
                System.out.println("Extraction complete.");
            } else {
                System.out.println("No action specified. Use -compress or -extract.");
            }
        } catch (ParseException e) {
            // Handle parse errors
            System.out.println("Error parsing the command line arguments: " + e.getMessage());
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("BurrowsWheeler.jar", options);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
