/**
 * 
 */
package com.renaissance.etl.util

import java.util.concurrent.*;

ExecutorService pool = Executors.newFixedThreadPool(10);
(1..100).each{index->
      def run = {println "this is the "+index+"th thread."}
      def future = pool.submit(run as Callable<String>);
}
pool.shutdown();



