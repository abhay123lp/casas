/**
 * 
 */
package com.renaissance.etl


file = new File("E:/SVN/renaissance/data/000001/2009-08-25.txt");
file.eachLine{line->
    tockens = line.split('\t');
    println tockens.join(',')
}
