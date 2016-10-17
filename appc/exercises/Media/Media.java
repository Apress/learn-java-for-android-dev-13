import java.io.IOException;
import java.io.RandomAccessFile;

public class Media
{
   public static class ID3
   {
      private String songTitle, artist, album, year, comment, genre;
      private int track; // -1 if track not present
      public ID3(String songTitle, String artist, String album, String year,
                 String comment, int track, String genre)
      {
         this.songTitle = songTitle;
         this.artist = artist;
         this.album = album;
         this.year = year;
         this.comment = comment;
         this.track = track;
         this.genre = genre;
      }
      String getSongTitle() { return songTitle; }
      String getArtist() { return artist; }
      String getAlbum() { return album; }
      String getYear() { return year; }
      String getComment() { return comment; }
      int getTrack() { return track; }
      String getGenre() { return genre; }
   }

   public static ID3 getID3Info(String mp3path) throws IOException
   {
      RandomAccessFile raf = null;
      try
      {
         raf = new RandomAccessFile(mp3path, "r");
         if (raf.length() < 128)
            return null; // Not MP3 file (way too small)
         raf.seek(raf.length() - 128);
         byte[] buffer = new byte[128];
         raf.read(buffer);
         raf.close();
         if (buffer[0] != (byte) 'T' && buffer[1] != (byte) 'A' &&
             buffer[2] != (byte) 'G')
            return null; // No ID3 block (must start with TAG)
         String songTitle = new String(buffer, 3, 30);
         String artist = new String(buffer, 33, 30);
         String album = new String(buffer, 63, 30);
         String year = new String(buffer, 93, 4);
         String comment = new String(buffer, 97, 28);
         // buffer[126] & 255 converts -128 through 127 to 0 through 255
         int track = (buffer[125] == 0) ? buffer[126] & 255 : -1;
         String[] genres = new String[]
                           {
                              "Blues",
                              "Classic Rock",
                              "Country",
                              "Dance",
                              "Disco",
                              "Funk",
                              "Grunge",
                              "Hip-Hop",
                              "Jazz",
                              "Metal",
                              "New Age",
                              "Oldies",
                              "Other",
                              "Pop",
                              "R&B",
                              "Rap",
                              "Reggae",
                              "Rock",
                              "Techno",
                              "Industrial",
                              "Alternative",
                              "Ska",
                              "Death Metal",
                              "Pranks",
                              "Soundtrack",
                              "Euro-Techno",
                              "Ambient",
                              "Trip-Hop",
                              "Vocal",
                              "Jazz+Funk",
                              "Fusion",
                              "Trance",
                              "Classical",
                              "Instrumental",
                              "Acid",
                              "House",
                              "Game",
                              "Sound Clip",
                              "Gospel",
                              "Noise",
                              "AlternRock",
                              "Bass",
                              "Soul",
                              "Punk",
                              "Space",
                              "Meditative",
                              "Instrumental Pop",
                              "Instrumental Rock",
                              "Ethnic",
                              "Gothic",
                              "Darkwave",
                              "Techno-Industrial",
                              "Electronic",
                              "Pop-Folk",
                              "Eurodance",
                              "Dream",
                              "Southern Rock",
                              "Comedy",
                              "Cult",
                              "Gangsta",
                              "Top 40",
                              "Christian Rap",
                              "Pop/Funk",
                              "Jungle",
                              "Native American",
                              "Cabaret",
                              "New Wave",
                              "Psychedelic",
                              "Rave",
                              "Showtunes",
                              "Trailer",
                              "Lo-Fi",
                              "Tribal",
                              "Acid Punk",
                              "Acid Jazz",
                              "Polka",
                              "Retro",
                              "Musical",
                              "Rock & Roll",
                              "Hard Rock",
                              "Folk",
                              "Folk-Rock",
                              "National-Folk",
                              "Swing",
                              "Fast Fusion",
                              "Bebob",
                              "Latin",
                              "Revival",
                              "Celtic",
                              "Bluegrass",
                              "Avantegarde",
                              "Gothic Rock",
                              "Progressive Rock",
                              "Psychedelic Rock",
                              "Symphonic Rock",
                              "Slow Rock",
                              "Big Band",
                              "Chorus",
                              "Easy Listening",
                              "Acoustic",
                              "Humour",
                              "Speech",
                              "Chanson",
                              "Opera",
                              "Chamber Music",
                              "Sonata",
                              "Symphony",
                              "Booty Brass",
                              "Primus",
                              "Porn Groove",
                              "Satire",
                              "Slow Jam",
                              "Club",
                              "Tango",
                              "Samba",
                              "Folklore",
                              "Ballad",
                              "Power Ballad",
                              "Rhythmic Soul",
                              "Freestyle",
                              "Duet",
                              "Punk Rock",
                              "Drum Solo",
                              "A cappella",
                              "Euro-House",
                              "Dance Hall"
                           };
         assert genres.length == 126;
         String genre = (buffer[127] < 0 || buffer[127] > 125)
                        ? "Unknown" : genres[buffer[127]];
         return new ID3(songTitle, artist, album, year, comment, track, genre);
      }
      catch (IOException ioe)
      {
         if (raf != null)
            try
            {
               raf.close();
            }
            catch (IOException ioe2)
            {
               ioe2.printStackTrace();
            }
         throw ioe;
      }
   }
}