import java.util.*;
import core.data.*;

public class KansupadaA29
{
	public static void main(String[] args)
	{
		ArrayList<WeatherStation> stations = WeatherMultiThread.getStations();
		//System.out.println(stations.size());
		//System.out.println(stations.get(0).getLoc().substring(stations.get(0).getLoc().length()-2));
		ArrayList<WeatherStation> texasStations = loneStarStations(stations);
		//System.out.println(texasStations.size());
		//System.out.println(texasStations.get(0).getLoc().substring(texasStations.get(0).getLoc().length()-2));
		System.out.println("\n\nThe temperature range in Texas is "+temperatureRange(texasStations)+" degrees.\n\n");
		System.out.println("Avaliable Stations:\n");
		//System.out.println(texasStations);
		displayStationsNeatly(texasStations);
		WeatherStation chosen = pickStation(texasStations);
		ArrayList<WeatherStation> Tstations = findByTemp(stations, chosen);
		for(int i = 0; i < Tstations.size();i++){
			Tstations.get(i).printS();
		}

	}
	public static ArrayList<WeatherStation> loneStarStations(ArrayList<WeatherStation> northAmerica)
	{
		ArrayList<WeatherStation> e = new ArrayList<WeatherStation>();
		System.out.println(northAmerica.size());
		for(int i = 0; i < northAmerica.size(); i++){
			if(northAmerica.get(i).isTexas()==true){
				e.add(northAmerica.get(i));
			}
		}
		//System.out.println(e.size());
		return e;
	}
	public static double temperatureRange(ArrayList<WeatherStation> list)
	{
		double max = list.get(0).getTemp();
		double min = list.get(0).getTemp();
		for(int i = 0; i < list.size();i++){
			if(list.get(i).getTemp() > max){
				max = list.get(i).getTemp();
			}
			if(list.get(i).getTemp() < max){
				min = list.get(i).getTemp();
			}
		}
		return max-min;
	}
	public static void displayStationsNeatly(ArrayList<WeatherStation> toDisplay)
	{
		for(int i = 0; i < toDisplay.size();i++){
			System.out.print(toDisplay.get(i).getID()+ " ");
		}

	}
	public static WeatherStation pickStation(ArrayList<WeatherStation> choices)
	{
		displayStationsNeatly(choices);
		Scanner s = new Scanner(System.in);
		boolean a = false;
		int pos = 0;
		while(!a){
		System.out.print("\nSelect a station\t");
		String id2 = s.nextLine();
		for(int i = 0; i < choices.size();i++){
			if(id2.equals(choices.get(i).getID())){
				a = true;
				pos = i;
			}
		}
		}

		return choices.get(pos);
	}
	public static ArrayList<WeatherStation> findByTemp(ArrayList<WeatherStation> all, WeatherStation choice)
	{
		 ArrayList<WeatherStation> bruh = new  ArrayList<WeatherStation>();
		for(int i = 0; i < all.size(); i++){
			if(choice.getTemp() == all.get(i).getTemp()){
				bruh.add(all.get(i));
			}
		}
		return bruh;
	}
}


class WeatherMultiThread
{
	public static int stationNumber=0;
	public static ArrayList<WeatherStation> stations = new ArrayList<WeatherStation>();
	public static ArrayList<String> station_urls = getStationList();
	public static ArrayList<WeatherStation> getStations()
	{
		long startTime = System.currentTimeMillis();
		System.out.println("There are "+station_urls.size()+" possibly available North American weather stations.");
		System.out.print("Loading stations -----\tThis could take several minutes   ");
		ArrayList<NetworkThread> threads = new ArrayList<NetworkThread>();
		for(int i=0;i<10;i++)
		{
			NetworkThread temp = new NetworkThread();
			temp.start();
			threads.add(temp);
			try{Thread.sleep(50);}catch(Exception e){}
		}
		boolean running = true;
		while(running)
		{
			running = false;
			for(NetworkThread nt : threads)
				if(nt.running())
					running = true;
		}
		startTime = System.currentTimeMillis()-startTime;
		System.out.println("\nLoad time :"+(startTime/1000)+" seconds");

		return stations;
	}
	/* Creates and returns an ArrayList of Strings representing
	 * the websites for all available weather station xml files
	 * @return 	a reference to an ArrayList containing String representations
	 *			of the websites for all available weather station xml files
	 */
	public static ArrayList<String> getStationList()
	{
		DataSource ds1 = DataSource.connect("https://w1.weather.gov/xml/current_obs/index.xml");
		ds1.setCacheTimeout(15 * 60);
      	ds1.load();
      	return ds1.fetchList("String","station/xml_url");
	}


}
class WeatherStation
{
	private String loc;
	private String ID;
	private double temp;

	public WeatherStation(String a, String b, double c){
		loc = a;
		ID = b;
		temp = c;
	}

	public boolean isTexas(){
		if(loc.contains("TX")){
			//System.out.println(true);
			return true;
		}
		//System.out.println(false);
		return false;
	}

	public void printS(){
		System.out.println(ID+"\t\t"+temp+"\t"+loc);
	}

	public String getID(){
		return ID;
	}

	public double getTemp(){
		return temp;
	}


	public String getLoc(){
		return loc;
	}
}


class NetworkThread implements Runnable
{

	private Thread t;
	private boolean running;
	public void run()
	{
		while(running)
		try
		{
			WeatherMultiThread.stations.add(createStation(WeatherMultiThread.station_urls.get(WeatherMultiThread.stationNumber++)));

		}
		catch(core.access.DataAccessException cae)
		{

		}
		catch(Exception e)
		{
			running=false;
		}
	}
	public boolean running(){return running;}
	public void start()
	{
		if(t==null)
		{
			running=true;
			t= new Thread(this,"network");
			t.start();
		}
	}
	/* Creates and returns a WeatherStation object.
	 * The method returns null to avoid compile errors, you will have
	 * to change this.
	 * @param 	stationAddress  the website for the xml file
	 * @return 	a reference to the WeatherStation object found at the
	 *			parameter stationAddress
	 */
	public static WeatherStation createStation(String stationAddress)
	{
		DataSource ds1 = DataSource.connect(stationAddress);
		ds1.setCacheTimeout(15 * 60);
      	ds1.load();
		//create and return the WeatherStation object here

		return new WeatherStation(ds1.fetch("String", "location"), ds1.fetch("String", "station_id"), Double.parseDouble(ds1.fetch("String", "temp_f")));
	}

}