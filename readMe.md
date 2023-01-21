# Priority Queue Project

In my project I am using two priority queues. In the project description these are denoted D and Q. Priority queue D is used to store all the processes from the input file based on arrival time. Priority queue Q is used to store the current processes based on their process priority
Observations and what I learned; At the beginning of the project I thought that the processes will be solely prioritized based upon their priority. However I learned that even though there are processes with lower priority in D, these are not the ones that will necessarily be executed first. This is because they have not made their way into Q yet, due to their arrival time.
