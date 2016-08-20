package com.books.saumy.books;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Created by Saumy on 10-04-2015.
 */
public class BookHelper extends SQLiteOpenHelper {
    byte[] data;
    private Context context;
    private static final String DATABASE_NAME="BOOKINFO.DB";
    private static final int DATABASE_VERSION=1;
    private static final String CREATE_QUERY = "CREATE TABLE "+ Bookdb.NewBookInfo.TABLE_NAME+"("+ Bookdb.NewBookInfo.BOOK_NAME+" TEXT,"+ Bookdb.NewBookInfo.AUTHOR_NAME+" TEXT,"+ Bookdb.NewBookInfo.GENRE+" TEXT,"+Bookdb.NewBookInfo.REVIEW+" TEXT,"+Bookdb.NewBookInfo.IMAGE+" BLOB,"+Bookdb.NewBookInfo.RATING+" TEXT)";
    public BookHelper(Context current){
        super(current,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS","DATABASE CREATED/OPENED");
        this.context=current;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
      /*  Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.harrypotterandthedeathlyhallows);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        data = bos.toByteArray();*/
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS","TABLE CREATED");
        data=imageToByteArray(R.drawable.hp1);
        addInfo("Harry Potter and the Philosophers Stone","JK Rowling","Fantasy","The Philosopher’s Stone is the first in J.K. Rowling’s Harry Potter series of seven novels that have made her the most successful literary author of all time, selling in excess of 400 million copies world-wide. The books are read and enjoyed by children and adults alike and have also been made into hugely popular films.",db);
        data=imageToByteArray(R.drawable.hobbit);
        addInfo("The Hobbit","JRR Tolkien","Fantasy","Gandalf tricks Bilbo into hosting a party for Thorin and his band of dwarves, who sing of reclaiming the Lonely Mountain and its vast treasure from the dragon Smaug. When the music ends, Gandalf unveils a map showing a secret door into the Mountain and proposes that the dumbfounded Bilbo serve as the expedition's \"burglar\". The dwarves ridicule the idea, but Bilbo, indignant, joins despite himself.\n" +
                "The group travels into the wild, where Gandalf saves the company from trolls and leads them to Rivendell, where Elrond reveals more secrets from the map. Passing over the Misty Mountains, they are caught by goblins and driven deep underground. Although Gandalf rescues them, Bilbo gets separated from the others as they flee the goblins. Lost in the goblin tunnels, he stumbles across a mysterious ring and then encounters Gollum, who engages him in a game of riddles. As a reward for solving all riddles Gollum will show him the path out of the tunnels, but if Bilbo fails, his life will be forfeit. With the help of the ring, which confers invisibility, Bilbo escapes and rejoins the dwarves, improving his reputation with them. The goblins and Wargs give chase but the company are saved by eagles before resting in the house of Beorn.\n" +
                "The company enters the black forest of Mirkwood without Gandalf. In Mirkwood, Bilbo first saves the dwarves from giant spiders and then from the dungeons of the Wood-elves. Nearing the Lonely Mountain, the travellers are welcomed by the human inhabitants of Lake-town, who hope the dwarves will fulfil prophecies of Smaug's demise. The expedition travels to the Lonely Mountain and finds the secret door; Bilbo scouts the dragon's lair, stealing a great cup and learning of a weakness in Smaug's armour. The enraged dragon, deducing that Lake-town has aided the intruder, sets out to destroy the town. A thrush had overheard Bilbo's report of Smaug's vulnerability and reports it to Lake-town defender Bard. His arrow finds the chink and slays the dragon.\n" +
                "When the dwarves take possession of the mountain, Bilbo finds the Arkenstone, an heirloom of Thorin's dynasty, and hides it away. The Wood-elves and Lake-men besiege the mountain and request compensation for their aid, reparations for Lake-town's destruction, and settlement of old claims on the treasure. Thorin refuses and, having summoned his kin from the mountains of the North, reinforces his position. Bilbo tries to ransom the Arkenstone to head off a war, but Thorin is intransigent. He banishes Bilbo, and battle seems inevitable.\n" +
                "Gandalf reappears to warn all of an approaching army of goblins and Wargs. The dwarves, men and elves band together, but only with the timely arrival of the eagles and Beorn do they win the climactic Battle of Five Armies. Thorin is fatally wounded and reconciles with Bilbo before he dies. Bilbo accepts only a small portion of his share of the treasure, having no want or need for more, but still returns home a very wealthy hobbit.",db,"4.7/5");
        data=imageToByteArray(R.drawable.titanscursenew);
        addInfo("Percy Jackson and the Titans Curse","Rick Riordan","Fantasy","The story starts out with Percy, Annabeth, and Thalia trying to help Grover to sneak out two half-bloods from a private military style school. Things get immediately messy and they stumble upon yet another problem, or more like a crisis. In the process of a battle Annabeth is stolen away. Something called The General is out again wrecking havoc and as you can already tell Luke has a hand in all this. This time around though you see Luke for what he really is, a kid trying to play with the big boys.\n" +
                "We get to see the goddess Artemis and her huntresses. Quite an intriguing bunch, eternal life if you don't fall in love? Kind of harsh, but I guess the price you pay matches your reward. The second in command of the huntresses, Zoe, would have to be my favorite new character. She's prickly but you learn throughout the book why she is the way she is. Bianca and Nico are the new half-bloods and will prove to play important roles in how the plot moves along.\n" +
                "It never fails to impress me how the author can intricately put everything into these books. I never feel bored and I love the view he takes on different mythology stories. After reading this book I'll never look at Hercules the same. One of my biggest enjoyments is seeing different American landmarks mentioned in the story, and seeing their odd connection to the Greek myths. You can tell the books are going to take a bit of a darker turn. It makes sense since they all are getting older and the inevitable prophecy is looming over.",db);
        data=imageToByteArray(R.drawable.holybiblecover);
        addInfo("Holy Bible","Jesus Christ","Religion","The New International Version (NIV) is a completely original translation of the Bible developed by more than one hundred scholars working from the best available Hebrew, Aramaic, and Greek texts.\n" +
                "The initial vision for the project was provided by a single individual – an engineer working with General Electric in Seattle by the name of Howard Long. Long was a lifelong devotee of the King James Version, but when he shared it with his friends he was distressed to find that it just didn’t connect. Long saw the need for a translation that captured the truths he loved in the language that his contemporaries spoke.\n" +
                "For 10 years, Long and a growing group of like-minded supporters drove this idea. The passion of one man became the passion of a church, and ultimately the passion of a whole group of denominations. And finally, in 1965, after several years of preparatory study, a trans-denominational and international group of scholars met in Palos Heights, Illinois, and agreed to begin work on the project – determining to not simply adapt an existing English version of the Bible but to start from scratch with the best available manuscripts in the original languages. Their conclusion was endorsed by a large number of church leaders who met in Chicago in 1966.\n" +
                "A self-governing body of fifteen biblical scholars, the Committee on Bible Translation (CBT) was formed and charged with responsibility for the version, and in 1968 the New York Bible Society (which subsequently became the International Bible Society and then Biblica) generously undertook the financial sponsorship of the project. The translation of each book was assigned to translation teams, each made up of two lead translators, two translation consultants, and a stylistic consultant where necessary. The initial translations produced by these teams were carefully scrutinized and revised by intermediate editorial committees of five biblical scholars to check them against the source texts and assess them for comprehensibility. Each edited text was then submitted to a general committee of eight to twelve members before being distributed to selected outside critics and to all members of the CBT in preparation for a final review. Samples of the translation were tested for clarity and ease of reading with pastors, students, scholars, and lay people across the full breadth of the intended audience. Perhaps no other translation has undergone a more thorough process of review and revision. From the very start, the NIV sought to bring modern Bible readers as close as possible to the experience of the very first Bible readers: providing the best possible blend of transparency to the original documents and comprehension of the original meaning in every verse. With this clarity of focus, however, came the realization that the work of translating the NIV would never be truly complete. As new discoveries were made about the biblical world and its languages, and as the norms of English usage developed and changed over time, the NIV would also need to change to hold true to its original vision.\n" +
                "And so in the original NIV charter, provision was made not just to issue periodic updates to the text but also to create a mechanism for constant monitoring of changes in biblical scholarship and English usage. The CBT was charged to meet every year to review, maintain, and strengthen the NIV’s ability to accurately and faithfully render God’s unchanging Word in modern English.",db);
        data=imageToByteArray(R.drawable.tellme);
        addInfo("Tell Me Your Dreams","Sidney Sheldon","Thriller","Computer whiz Ashley Patterson is convinced she is being stalked. Coworker Toni Prescott has a penchant for Internet dating and little time for anyone else. And Alette Peters prefers quiet weekends in the arms of a beefcake artist. They know virtually nothing about each other--until the three women are linked by a murder investigation that will lead to one of the most bizarre trials of the century.",db);
        data=imageToByteArray(R.drawable.animorphs);
        addInfo("Animorphs","KA Applegate","Fantasy","The story revolves around five humans, Jake, Marco, Cassie, Rachel, and Tobias, and one alien, Aximili-Esgarrouth-Isthill (nicknamed Ax), who obtain the ability to morph into any animal they touch. Naming themselves \"Animorphs\" (a portmanteau of \"animal morphers\"),[4] they use their ability to battle a secret alien infiltration of Earth by a race called the Yeerks. The Yeerks are a parasitic race of aliens resembling large slugs that take humans as a host by entering and merging with their brain through the ear canal. The Animorphs fight as a guerilla force against the Yeerks, led by Visser Three, and their program to take over the human race. Morphing into animals allows them to battle the various armies of aliens that the Yeerks use, but it also protects their identities. As far as the Yeerks know, only Andalites like Ax have the ability to morph, and if they knew that the Animorphs were mostly human they would be able to easily find out who they are. Protecting their identities becomes more and more difficult as the series goes on because although someone with the ability to morph can change into any animal that they touch, they can only stay in a morph for two hours or they will permanently become that animal. Throughout the series, we see how the war affects the characters in different ways, mentally and physically.",db);
        data=imageToByteArray(R.drawable.davincicode);
        addInfo("The DaVinci Code","Dan Brown","Thriller","An ingenious code hidden in the works of Leonardo da Vinci. A desperate race through the cathedrals and castles of Europe. An astonishing truth concealed for centuries . . . unveiled at last.\n" +
                "While in Paris, Harvard symbologist Robert Langdon is awakened by a phone call in the dead of the night. The elderly curator of the Louvre has been murdered inside the museum, his body covered in baffling symbols. As Langdon and gifted French cryptologist Sophie Neveu sort through the bizarre riddles, they are stunned to discover a trail of clues hidden in the works of Leonardo da Vinci—clues visible for all to see and yet ingeniously disguised by the painter.\n" +
                "Even more startling, the late curator was involved in the Priory of Sion—a secret society whose members included Sir Isaac Newton, Victor Hugo, and Da Vinci—and he guarded a breathtaking historical secret. Unless Langdon and Neveu can decipher the labyrinthine puzzle—while avoiding the faceless adversary who shadows their every move—the explosive, ancient truth will be lost forever. ",db);
        data=imageToByteArray(R.drawable.famousfive);
        addInfo("Famous Five","Enid Blyton","Mystery","The very first Famous Five adventure, featuring Julian, Dick, Anne, not forgetting tomboy George and her beloved dog, Timmy! There's a shipwreck off Kirrin Island! But where is the treasure? The Famous Five are on the trail - looking for clues - but they're not alone! Someone else has got the same idea. Time is running out for the Famous Five, who will follow the clues and get to the treasure first?",db);
        data=imageToByteArray(R.drawable.harrypotterandthedeathlyhallows);
        addInfo("Harry Potter and Deathly Hallows","JK Rowling","Fantasy","Harry is waiting in Privet Drive. The Order of the Phoenix is coming to escort him safely away without Voldemort and his supporters knowing - if they can. But what will Harry do then? How can he fulfill the momentous and seemingly impossible task that Professor Dumbledore has left him?\n" +
                "The epic finale to an epic series.",db);
        data=imageToByteArray(R.drawable.inferno);
        addInfo("Inferno","Dan Brown","Thriller","In his international blockbusters The Da Vinci Code, Angels & Demons, and The Lost Symbol, Dan Brown masterfully fused history, art, codes, and symbols. In this riveting new thriller, Brown returns to his element and has crafted his highest-stakes novel to date.\n" +
                "In the heart of Italy, Harvard professor of symbology Robert Langdon is drawn into a harrowing world centered on one of history’s most enduring and mysterious literary masterpieces . . . Dante’s Inferno.\n" +
                "Against this backdrop, Langdon battles a chilling adversary and grapples with an ingenious riddle that pulls him into a landscape of classic art, secret passageways, and futuristic science. Drawing from Dante’s dark epic poem, Langdon races to find answers and decide whom to trust . . . before the world is irrevocably altered.",db);
        data=imageToByteArray(R.drawable.olympus);
        addInfo("Heroes of olympus - The Mark of Athena","Rick Riordan","Fantasy","Annabeth is terrified. Just when she's about to be reunited with Percy—after six months of being apart, thanks to Hera—it looks like Camp Jupiter is preparing for war. As Annabeth and her friends Jason, Piper, and Leo fly in on the Argo II, she can’t blame the Roman demigods for thinking the ship is a Greek weapon. With its steaming bronze dragon masthead, Leo's fantastical creation doesn't appear friendly. Annabeth hopes that the sight of their praetor Jason on deck will reassure the Romans that the visitors from Camp Half-Blood are coming in peace.\n" +
                "And that's only one of her worries. In her pocket Annabeth carries a gift from her mother that came with an unnerving demand: Follow the Mark of Athena. Avenge me. Annabeth already feels weighed down by the prophecy that will send seven demigods on a quest to find—and close—the Doors of Death. What more does Athena want from her?\n" +
                "Annabeth's biggest fear, though, is that Percy might have changed. What if he's now attached to Roman ways? Does he still need his old friends? As the daughter of the goddess of war and wisdom, Annabeth knows she was born to be a leader, but never again does she want to be without Seaweed Brain by her side.\n" +
                "Narrated by four different demigods, The Mark of Athena is an unforgettable journey across land and sea to Rome, where important discoveries, surprising sacrifices, and unspeakable horrors await. Climb aboard the Argo II, if you dare.... ",db);
        data=imageToByteArray(R.drawable.frank);
        addInfo("Totally Frank","Frank Lampard","Autobiography","Chelsea and England footballer Frank Lampard charts his life story from childhood to young West Ham apprentice to multi-millionaire world footballing celebrity and lynchpin of the national team. In his book, Lampard opens up on his early years, how he dealt with the fame and fortune that has come his way since becoming a key member of the England side, his frank opinions on former England boss Sven-Goran Eriksson and his manager at Chelsea Jose Mourinho, fascinating insights into Roman Abramovich and revealing tales on his current team-mates. He reveals both the privileges and the pressures of being one of the 'golden generation' of England players. He gives a fascinating inside account of World Cup 2006 in Germany, and describes the disappointment of not fulfilling the dream of bringing the biggest prize in football back to England.",db);
        data=imageToByteArray(R.drawable.sachin);
        addInfo("Playing it my way","Sachin Tendulkar","Autobiography","\"I’m delighted that my autobiography #PlayingitMyWay will be published on November 6, this year. \n" +
                "I knew that agreeing to write my story would need me to be completely honest, as that’s the way I have always played the game. It would require talking about a number of aspects I have not shared in public before. \n" +
                "So here I am, at the end of my final innings, having taken that last walk back to the pavilion, ready to recount as many incidents as I can remember since first picking up a cricket bat as a child in Mumbai thirty-five years ago.\" - Sachin Tendulkar",db);
        data=imageToByteArray(R.drawable.sons);
        addInfo("Sons of Fortune","Jeffrey Archer","Fiction","It is often spur-of-the-moment decisions, sometimes made by others, that can change our whole lives.\n" +
                "Bestselling author Jeffrey Archer returns with a powerful tale of twins separated by fate and reunited by destiny.\n" +
                "In the late 1940's in Hartford, Connecticut a set of twins is parted at birth. Nat Cartwright goes home with his parents, a schoolteacher and an insurance salesman. But his twin brother is to begin his days as Fletcher Andrew Davenport, the only son of a multi-millionaire and his society wife. \n" +
                "During the years that follow, the two brothers grow up unaware of each other's existence. Nat leaves college at the University of Connecticut to serve in Vietnam. He returns a war hero, finishes school and becomes a successful banker. Fletcher, meanwhile, has graduated from Yale University and distinguishes himself as a criminal defense lawyer before he is elected to the Senate. \n" +
                "Even when Nat and Fletcher fall in love with the same girl they still don't meet. They continue on their separate paths until one has to defend the other for a murder he did not commit. But the final confrontation comes when Nat and Fletcher are selected to stand against each other for governor of the state.\n" +
                "In the tradition of Jeffrey Archer's most popular books, Sons of Fortune is as much a chronicle of a nation in transition as it is the story of two remarkable men and how, eventually, they come to discover the truth - and its extraordinary consequences.",db);

      /*  db.execSQL("INSERT INTO book_info VALUES('Harry Potter','JK Rowling','Fantasy','This book is awesome!',"+data+")");
        db.execSQL("INSERT INTO book_info VALUES('Percy Jackson','Rick Riordan','Fantasy','This book is awesome!',"+data+")");
        db.execSQL("INSERT INTO book_info VALUES('The Hobbit','JRR Tolkien','Fantasy','This book is awesome!',"+data+")");
        db.execSQL("INSERT INTO book_info VALUES('Angels and Demons','Dan Brown','Thriller','This book is awesome!',"+data+")");
        db.execSQL("INSERT INTO book_info VALUES('Famous Five','Enid Blyton','Mystery','This book is awesome!',"+data+")");
        db.execSQL("INSERT INTO book_info VALUES('Tell Me Your Dreams','Sidney Sheldon','Thriller','This book is awesome!',"+data+")");
        db.execSQL("INSERT INTO book_info VALUES('Holy Bible','Jesus Christ','Religion','This book is awesome!',"+data+")");
        db.execSQL("INSERT INTO book_info VALUES('Lord of the Rings','JK Rowling','Fantasy','This book is awesome!',"+data+")");
        db.execSQL("INSERT INTO book_info VALUES('Harry Potter 2','JK Rowling','Fantasy','This book is awesome!',"+data+")");
        db.execSQL("INSERT INTO book_info VALUES('Harry Potter 3','JK Rowling','Fantasy','This book is awesome!',"+data+")");
        db.execSQL("INSERT INTO book_info VALUES('Harry Potter 4','JK Rowling','Fantasy','This book is awesome!',"+data+")");
        db.execSQL("INSERT INTO book_info VALUES('Heroes of Olympus','Ric Riordan','Fantasy','This book is awesome!',"+data+")");
        db.execSQL("INSERT INTO book_info VALUES('Bloodline','Sidney Sheldon','Thriller','This book is awesome!',"+data+")");
        db.execSQL("INSERT INTO book_info VALUES('Sons of Fortune','Jefferey Archer','Fiction','This book is awesome!',"+data+")");
        db.execSQL("INSERT INTO book_info VALUES('Kane and Abel','Jefferey Archer','Fiction','This book is awesome!',"+data+")");*/
        Log.e("DATABASE OPERATIONS","VALUES ENTERED");
    }
    public byte[] imageToByteArray(int path){
        Bitmap bmp = BitmapFactory.decodeResource(context.getResources(),path);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        return bos.toByteArray();
    }
    public void addInfo(String name,String author,String genre,String review,SQLiteDatabase db){
        ContentValues contentvalues=new ContentValues();
        contentvalues.put(Bookdb.NewBookInfo.BOOK_NAME,name);
        contentvalues.put(Bookdb.NewBookInfo.AUTHOR_NAME,author);
        contentvalues.put(Bookdb.NewBookInfo.GENRE,genre);
        contentvalues.put(Bookdb.NewBookInfo.REVIEW,review);
        contentvalues.put(Bookdb.NewBookInfo.IMAGE,data);
        contentvalues.put(Bookdb.NewBookInfo.RATING,"null");
        db.insert(Bookdb.NewBookInfo.TABLE_NAME,null,contentvalues);
        Log.e("DATABASE OPERATIONS","ROW INSERTED");
    }
    public void addInfo(String name,String author,String genre,String review,SQLiteDatabase db,String rating){
        ContentValues contentvalues=new ContentValues();
        contentvalues.put(Bookdb.NewBookInfo.BOOK_NAME,name);
        contentvalues.put(Bookdb.NewBookInfo.AUTHOR_NAME,author);
        contentvalues.put(Bookdb.NewBookInfo.GENRE,genre);
        contentvalues.put(Bookdb.NewBookInfo.REVIEW,review);
        contentvalues.put(Bookdb.NewBookInfo.IMAGE,data);
        contentvalues.put(Bookdb.NewBookInfo.RATING,rating);
        db.insert(Bookdb.NewBookInfo.TABLE_NAME,null,contentvalues);
        Log.e("DATABASE OPERATIONS","ROW INSERTED");
    }
    public Cursor getDetails(SQLiteDatabase db) {
        Cursor cursor;
        //String[] projections = {Bookdb.NewBookInfo.BOOK_NAME, Bookdb.NewBookInfo.AUTHOR_NAME, Bookdb.NewBookInfo.GENRE, Bookdb.NewBookInfo.REVIEW};
        //cursor=db.query(Bookdb.NewBookInfo.TABLE_NAME,projections,null,null,null,null,null);
        String selectQuery = "SELECT * FROM book_info";
        cursor = db.rawQuery(selectQuery, null);
        return cursor;
    }
    public Cursor getInfo(SQLiteDatabase db) {
        Cursor cursor;
        String[] projections = {Bookdb.NewBookInfo.BOOK_NAME, Bookdb.NewBookInfo.AUTHOR_NAME, Bookdb.NewBookInfo.IMAGE};
        cursor=db.query(Bookdb.NewBookInfo.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }
    public Cursor getInfo(String bookname,SQLiteDatabase sqLiteDatabase){
        Cursor cursor;
        String[] projections = {Bookdb.NewBookInfo.BOOK_NAME, Bookdb.NewBookInfo.AUTHOR_NAME, Bookdb.NewBookInfo.IMAGE};
        cursor=sqLiteDatabase.rawQuery("SELECT book_name,author_name,image FROM book_info WHERE genre like '"+bookname+"%'",null);
        return cursor;
    }
    public Cursor searchInfo(String bookname,SQLiteDatabase sqLiteDatabase,String criteria){
        Cursor cursor;
        String[] projections = {Bookdb.NewBookInfo.BOOK_NAME, Bookdb.NewBookInfo.AUTHOR_NAME, Bookdb.NewBookInfo.IMAGE};
        //String selection = Bookdb.NewBookInfo.BOOK_NAME+" LIKE ?";
        //String[] selection_args = {bookname};
        //cursor=sqLiteDatabase.query(Bookdb.NewBookInfo.TABLE_NAME,projections,selection,selection_args,null,null,null);
        cursor=sqLiteDatabase.rawQuery("SELECT book_name,author_name,image FROM book_info WHERE "+ criteria +" like '"+bookname+"%'",null);
        return cursor;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
