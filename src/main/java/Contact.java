public class Contact {
   private String lastName;
   private String thumbImage;
   private String largeImage;
   private String firstName;
   private String email;
   private String number;

   public Contact(String lastName, String thumbImage, String largeImage, String firstName, String email, String number) {
      this.lastName = lastName;
      this.thumbImage = thumbImage;
      this.largeImage = largeImage;
      this.firstName = firstName;
      this.email = email;
      this.number = number;
   }

   public String getLastName() {return lastName;}

   public void setLastName(String lastName) {this.lastName = lastName;}

   public String getThumbImage() {return thumbImage;}

   public void setThumbImage(String thumbImage) {this.thumbImage = thumbImage;}

   public String getLargeImage() {return largeImage;}

   public void setLargeImage(String largeImage) {this.largeImage = largeImage;}

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getNumber() {
      return number;
   }

   public void setNumber(String number) {
      this.number = number;
   }

   @Override
   public String toString() {return getLastName();}
}
