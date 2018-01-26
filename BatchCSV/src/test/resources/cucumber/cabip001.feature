# As part of this demo, data is assumed to be available in the right state
# in the Kenan and SKY schema database tables.
# In the actual implementation, steps will be written to load Kenan and Sky database tables as necessary.
Feature: Perform a regular cyclic bill run using the batch job cabip001
    Scenario Outline: Generate expected file for baseline run for kenan BIP process
        Given an account eligible for billing exists in the database
        When BIP process is executed
        Then expected files should be generated based on <config> file
        Examples:
        | config          |       
        | config_file.csv |
        
    Scenario Outline: Generate actual file for post upgrade run for kenan BIP process
        Given an account eligible for billing exists in the database
        When BIP process is executed
        Then actual files should be generated based on <config> file
        Examples:
        | config          |       
        | config_file.csv |
    
    @CompareFiles
    Scenario Outline: compare baseline and post upgrade files generated run for kenan BIP process
        Given baseline and post upgrade are executed succesfully creating <expected> and <actual> files
        Then compare the <expected> and <actual> files dynamically and verify the results
        Examples:
        | expected                  | actual                  |
        | CMF_expected.csv          | CMF_actual.csv          |
        | BILL_INVOICE_expected.csv | BILL_INVOICE_actual.csv |
        