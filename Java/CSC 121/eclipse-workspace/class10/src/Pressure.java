
// recording air pressure measurements [in hPa]

class Pressure extends ARecording {
  
  public Pressure(int high, int today, int low) {
    super(high, today, low, "hPa");
  }
}