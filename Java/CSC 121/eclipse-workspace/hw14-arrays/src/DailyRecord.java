/** Represents 24 hourly temperature readings */
public class DailyRecord {

	// declare an array of integers field
	int[] readings;
	
    public DailyRecord() {
    	// create an array of 24 elements
    	//   indexed from 0 ... 23
    	readings = new int[24];  
    	// by default, Java fills in array elements with
    	//    0, 0.0, false, null
    	
    	int i = 0;
        while ( i < readings.length ) {  // as long as i is less than 24
	         readings[ i ] = Integer.MIN_VALUE;
	         i = i + 1;
        }
    	
    	
    	
    	/*
    	
    	// to set a value at index 0 in the array:
    	readings[0] = 68;
    	readings[ <index> ] =  <value>  ;
    	
    	// to access a value at index in the array:
    	
    	....   readings[ <index> ] ....
    	int sumOf2 = readings[0] + readings[1];
    	
    	
    	 */
    	
    }
    
    
    /** 
     *  Records the given temperature value for the given
     *  hour (specified in 24-hour, or "military" time, 0 ... 23).
     */
    public void recordTemp(int hour, int temp) {
    	this.readings[ hour ] = temp;
    }
    
    /**
     * Returns the recorded temperature value for the given hour
     */
    public int getTemp(int hour) {
    	if (this.readings[ hour ] == Integer.MIN_VALUE) {
    		throw new RuntimeException("no data for " + hour);
    	} else {
    		return this.readings[ hour ];
    	}
    }
    
    /**
     * Returns the highest recorded temperature value
     */
    public int getHigh() {
    	 int maxReading = Integer.MIN_VALUE; // -2147483648  
         int maxReadingField = -1;

         int i = 0;
        
         while ( i < readings.length ) {  // as long as i is less than 24
	         if (readings[ i ] > maxReading) {
	             maxReading = readings[ i ];
	             maxReadingField = i;
	         } 
	         i = i + 1;
         }
         
         if (maxReadingField < 0) { // still negative 
        	 return -1000;
        	 // throw new RuntimeException("no readings recorded yet");
         } else {
        	 return maxReading;
         }
    }
    
    
    // LOOP = built-in structure in a language to
    //       abstract over repetition
    /*
    
    while ( <boolean-condition> ) {
         <body>
    }
    
    
     */
    
    
    /**
     * Returns the lowest recorded temperature value 
     */
    public int getLow() {
    	int minReading = Integer.MAX_VALUE; // 2147483648  
        int minReadingField = -1;

        int i = 0;
       
        while ( i < readings.length ) {  // as long as i is less than 24
        	if(readings [ i ] > Integer.MIN_VALUE && readings[ i ] < minReading) {
	             minReading = readings[ i ];
	             minReadingField = i;
	         } 
	         i = i + 1;
        }
        
        if (minReadingField < 0) { // still negative 
       	 return -1000;
       	 // throw new RuntimeException("no readings recorded yet");
        } else {
       	 return minReading;
        }
    }
    
    /**
     * Returns the average temperature
     */
    public int getAverage() {
    	int minValue = Integer.MIN_VALUE; // -2147483648  
        int numValues = 0;
        int avgAcc = 0;

        int i = 0;
       
        while ( i < readings.length ) {  // as long as i is less than 24
        	if(readings [ i ] > minValue) {
	             avgAcc = readings[ i ] + avgAcc;
	             numValues = numValues + 1;
	         } 
	         i = i + 1;
        }
        
        if (numValues < 0) { // still negative 
       	 return -1000;
       	 // throw new RuntimeException("no readings recorded yet");
        } else {
       	 return avgAcc/numValues;
        }
    }

    
    
    // auto-generated methods
    
    
    

}