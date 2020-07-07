# Coding challenge for Backend Development

See details here: http://blog.papauschek.com/2019/10/analytics-coding-challenge/

# Steps to improve the performance of 30 days statistic function
1) Conversion of all non primitive variable to primitive.
2) Take out the sort method from getLast30DaysStats() and place it in the save method save(Purchase purchase).
3) Convert all MapToDouble function to map reduce so that non primitive can be converted to primitive one.