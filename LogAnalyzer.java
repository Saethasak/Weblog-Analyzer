
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 7.0
 */
public class LogAnalyzer
{
    public static final int HOURS_PER_DAY = 24;
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly #5 #7
        // access counts.
        hourCounts = new int[HOURS_PER_DAY];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);

    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    // #4
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        int hour = 0;
        while(hour<hourCounts.length) {
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
    }

    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }

    /** * Return the number of accesses recorded in the log file. */ 
    public int numberOfAccesses() { 
        int total = 0;
        // Add the value in each element of hourCounts to total. ... #6
        for(int i=0; i < hourCounts.length; i++){
            total += hourCounts[HOURS_PER_DAY];
        }
        return total; 
    } 
        // #9
    public int quietestHour(){
        int quietestHour = 0;
        for(int i=0; i < hourCounts.length; i++){
            if(hourCounts[i]<hourCounts[quietestHour]){
                quietestHour = i;
            }
        }
        return quietestHour;
    }
    // #8 #10
    public int busiestHour(){
        int busiestHour = 0;
        for(int i=0; i < hourCounts.length; i++){
            if(hourCounts[i]>hourCounts[busiestHour]){
                busiestHour = i;
            }
        }
        return busiestHour;
    }
    // #11
    public int twinBusyHour(){
        int busiestHour=0;
        for(int i = 1; i < hourCounts.length-1;i++){
            if(hourCounts[i]+hourCounts[i+1]>hourCounts[busiestHour]+hourCounts[busiestHour+1]){
                busiestHour=i;
            }
        }
        return busiestHour;
    }
}
