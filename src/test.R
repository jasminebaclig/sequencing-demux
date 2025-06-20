library(tidyverse)

combined_1 <- read_csv("../index_list_combined_R1_1.csv", col_names = FALSE) %>% arrange(desc(X2))
combined_2 <- read_csv("../index_list_combined_R1.csv", col_names = FALSE) %>% arrange(desc(X2))
combined_no_array <- read_csv("../index_list_no_array_combined_R1.csv", col_names = FALSE) %>% arrange(desc(X2))
