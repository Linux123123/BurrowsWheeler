/******************************************************************************
 *  Name:      
 *  NetID:    
 *  Precept:  
 *
 *  Partner Name:     N/A
 *  Partner NetID:    N/A
 *  Partner Precept:  N/A
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/

Programming Assignment 8: Burrows-Wheeler



/******************************************************************************
 *  List in table format which input files you used to test your program.
 *  Fill in columns for how long your program takes to compress and
 *  decompress these instances (by applying BurrowsWheeler, MoveToFront,
 *  and Huffman in succession). Also, fill in the third column for
 *  the compression ratio (number of bytes in compressed message 
 *  divided by the number of bytes in the message).
 *****************************************************************************/

File     Encoding Time    Decoding time      Compression ratio
------------------------------------------------------------------------



/******************************************************************************
 *  Compare the results of your program (compression ratio and running
 *  time) on mobydick.txt to that of the most popular Windows
 *  compression program (pkzip) or the most popular Unix/Mac one (gzip).
 *  If you don't have pkzip, use 7zip and compress using zip format.
 *****************************************************************************/


/******************************************************************************
 *  Give the order of growth of the running time of each of the 6
 *  methods as a function of the input size N and the alphabet size R
 *  both in practice (on typical English text inputs) and in theory
 *  (in the worst case), e.g., N, N + R, N log N, N^2, or R N.
 *
 *  Include the time for sorting circular suffixes in the
 *  Burrows-Wheeler encoder.
 *****************************************************************************/

                                      typical            worst
---------------------------------------------------------------------
BurrowsWheeler transform()
BurrowsWheeler inverseTransform()
MoveToFront encode()
MoveToFront decode()
Huffman compress()                    N + R log R        N + R log R
Huffman expand()                      N                  N





/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/



/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/


/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/



/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it. Additionally, you may include any suggestions
 *  for what to change or what to keep (assignments or otherwise) in future 
 *  semesters.
 *****************************************************************************/
