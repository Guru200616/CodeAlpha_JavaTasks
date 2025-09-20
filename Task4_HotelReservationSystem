// Room.java
public class Room {
    private int roomNumber;
    private String category;
    private double price;
    private boolean booked;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.booked = false;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public boolean isBooked() { return booked; }

    public void bookRoom() { this.booked = true; }
    public void cancelBooking() { this.booked = false; }

    @Override
    public String toString() {
        return "Room " + roomNumber + " [" + category + "] - $" + price + 
               (booked ? " (Booked)" : " (Available)");
    }
}
