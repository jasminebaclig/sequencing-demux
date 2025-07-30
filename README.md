# Sequencing Demux (unfinished project)

The following describes my work and thought process throughout this project, which I mostly did just to figure out what's going on and why these reads are left unidentified:

- test.fastq contains the first ten entries in Undetermined_S0_L001_R1_001.fastq. Each entry starts with a line that looks like the one below. Based on some Googling, I think the last bit of information in this sequence identifier is the forward and reverse index/primer separated by '+'.

`@VL00155:45:AAGYHMHM5:1:1101:18269:1000 1:N:0:AGGGGGGGGG+GGGTCTCGGT`

- I wrote some code to extract the index of all entries in the FASTQ files of Read 1 and 2. The output can be found in index_list_R1.csv and index_list_R2.csv. These files contains a list of all unique indeces followed by how many times they appeared in their respective original FASTQ file. Here are some things that I noticed:
    - There are several indeces that appeared only a handful of times (sometimes even one), whereas some appeared millions of times (which is what I think should be expected).
    - As expected, the output files for Read 1 and 2 are identical.

- The code I mentioned above does only consolidate entries if their indeces match exactly, so I wrote new code to combine indeces even if they vary by one or two bases. The output files are CSV files that have "combined" in their name. I tried a couple of different approaches (described below), but in all of these files, each line contains all variations of an index (separated by tabs) that were deemed similar enough followed by how many reads contained any of the listed variation of an index.
    - index_list_combined_R1.csv allows for up to two bases to be different for two indeces to be considered similar (the forward and reverse indeces were treated separately). A new index is also checked against all of the variations of a given index, so some significant deviations may be observed from one index to another. **This run was not fully finished, however.**
    - index_list_combined_R1_1.csv does the same things as above but only one different base was allowed. Some significant deviations may still be observed especially if the list of variations is long. **This run was not fully finished, however.**
    - index_list_no_array_combined_R1.csv is similar to the one above, but a new index is only checked against the first variation of a given index. There is less deviation seen here, but I would imagine that the list will have more lines/entries than the first two approaches as some entries still have a frequency of one. It is still signicantly less than the number of entries in the original index_list_R1.csv (32,539 versus 609,446).