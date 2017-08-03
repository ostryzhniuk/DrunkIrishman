-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: drunkIrishman
-- ------------------------------------------------------
-- Server version	5.7.9-log

CREATE DATABASE IF NOT EXISTS drunkIrishman;
USE drunkIrishman;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `category_name_uindex` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'Whiskey',1),(2,'Beer',1),(3,'Samogon',1),(4,'Brandy',1),(5,'Cognac',1),(6,'Rum',1),(7,'Liqueurs',1),(8,'Wine',1),(9,'Gin',1),(10,'Champagne',1);
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_content`
--

DROP TABLE IF EXISTS `order_content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `order_content` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) NOT NULL,
  `product_id` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_orders_content_order1_idx` (`order_id`),
  KEY `orders_content_product_id_fk` (`product_id`),
  CONSTRAINT `fk_orders_content_order1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `orders_content_product_id_fk` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_content`
--

LOCK TABLES `order_content` WRITE;
/*!40000 ALTER TABLE `order_content` DISABLE KEYS */;
INSERT INTO `order_content` VALUES (1,1,1,1),(2,2,1,1),(3,3,2,1),(4,4,3,1),(5,5,1,5),(6,5,2,1),(7,6,2,1),(8,7,2,1),(9,8,1,7),(10,8,2,3),(11,8,3,1),(22,14,5,1),(23,15,5,1),(24,16,4,1),(25,17,1,1),(28,19,3,1),(29,20,4,1),(30,20,5,1),(34,23,4,1),(35,23,5,1),(38,25,1,1),(39,25,3,1),(40,25,4,1),(41,25,5,1),(42,26,1,5),(43,26,3,5),(44,26,4,7),(45,26,5,3),(46,27,18,2),(47,27,20,1),(48,28,49,2),(49,28,52,1),(50,28,10,1),(51,29,39,1),(52,29,42,1);
/*!40000 ALTER TABLE `order_content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orders` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `address` varchar(300) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `status` varchar(20) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `user_id` int(11) NOT NULL,
  `delivery` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_orders_user1_idx` (`user_id`),
  CONSTRAINT `fk_orders_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,'2017-08-01 22:30:59.000000','Matrix','0100110111','IS_SENT',65.50,8,0),(2,'2017-08-02 00:27:11.000000','Matrix','0100110111','IN_PROCESS',65.50,8,0),(3,'2017-08-02 00:44:36.000000','Matrix','0100110111','IN_PROCESS',1.75,8,0),(4,'2017-08-02 00:45:17.000000','Matrix','0100110111','COMPLETED',3.00,8,0),(5,'2017-08-02 00:46:11.000000','Matrix','0100110111','IN_PROCESS',329.25,8,1),(6,'2017-08-02 01:06:26.000000','Matrix','0100110111','IN_PROCESS',1.75,8,0),(7,'2017-08-02 01:44:59.000000','Matrixdfgsfgr','3463565475467','COMPLETED',1.75,8,1),(8,'2017-08-02 05:43:50.000000','Mexico','0934578653','IN_PROCESS',466.75,8,1),(14,'2017-08-02 23:38:29.000000','Matrix','0100110111','IS_SENT',3.05,8,0),(15,'2017-08-02 23:53:58.000000','Boston, Ukrainian st., 28','4769033212113','IN_PROCESS',3.05,11,1),(16,'2017-08-03 00:21:49.000000','Boston, Ukrainian st., 28','4769033212113','COMPLETED',2.80,11,1),(17,'2017-08-03 00:22:12.000000','Boston, Ukrainian st., 28','4769033212113','IN_PROCESS',65.50,11,0),(19,'2017-08-03 00:22:48.000000','Boston, Ukrainian st., 28','4769033212113','IS_SENT',3.00,11,1),(20,'2017-08-03 00:23:01.000000','Boston, Ukrainian st., 28','4769033212113','CANCELED',5.85,11,0),(23,'2017-08-03 00:29:06.000000','Boston, Ukrainian st., 28','4769033212113','COMPLETED',5.85,11,0),(25,'2017-08-03 02:01:53.000000','Matrix','0100110111','COMPLETED',74.35,8,0),(26,'2017-08-03 02:03:17.000000','Boston, Ukrainian st., 28','4769033212113','IS_SENT',371.25,11,1),(27,'2017-08-03 15:03:49.000000','Albion Street, Surry Hills, 64a Sydney, Australi','046236548234','CANCELED',27.47,12,1),(28,'2017-08-03 15:07:46.000000','Albion Street, Surry Hills, 64a Sydney, Australi','046236548234','COMPLETED',85.26,12,1),(29,'2017-08-03 15:08:01.000000','Albion Street, Surry Hills, 64a Sydney, Australi','046236548234','COMPLETED',46.98,12,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `capacity` decimal(5,2) NOT NULL DEFAULT '1.00',
  `price` decimal(10,2) NOT NULL,
  `category_id` int(11) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `status` varchar(20) NOT NULL,
  `isActive` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_product_category_idx` (`category_id`),
  CONSTRAINT `FKexqqeaksnmmku5py194ywp140` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Jameson',1.00,65.50,1,'When visiting the Old Jameson Distillery Dublin and The Jameson Experience Midleton, Co. Cork, not only can you immerse yourself in the world of Jameson but you can take away a truly unique souvenir - a Personalised bottle of Jameson 12 Year Old Distillery Reserve. A Distillery Reserve whiskey goes back to the tradition of the 1780s, when personal guests of John Jameson would be presented with a bottle by him at the end of their visit. This excellent Distillery Reserve has been aged in both Bourbon and Sherry Casks for a minimum of 12 years and has a high pure Pot Still content. As an exclusive to our online webshop customers, we are offering you the chance to buy this truly unique whiskey. Not only that, we also offer a personalisation service where each bottle can be made truly special with your own name printed on both the bottle and canister label. The perfect gifting idea!\\r\\nABV: 40%','SOLD_OUT',1),(2,'GUINNESS DRAUGHT',0.50,3.45,2,'Aroma: Sweet smelling with a coffee and malty nose\n\nFlavor: Perfect balance of bitter and sweet with malt and roast characters\n\nPalate: Smooth, creamy and balanced\n\nABV: 4.2%\n\nAppearance: Distinctively dark, with a rich creamy head','SOLD_OUT',1),(3,'Dragon Flame',1.00,3.00,3,'This is a real male drink. One sip and the world burns!\n\nABV: 65%','IN_STOCK',1),(4,'By Ostryzhniuk',1.00,2.80,3,'Cool drink for your party.\nSoft taste, strong alcohol and head doesn\'t ache.\n\nABV: 51%','IN_STOCK',1),(5,'Coronita Extra Loose Beer',0.50,3.25,2,'Mexico- The most popular imported beer now comes in a smaller bottle!\nLagers represent some of the most approachable, easy-drinking beer styles available, which contributes to Lager’s consumption edge over ale, despite the fact that ale styles are more numerous and were consumed thousands of years before lagering and cold-aging took hold.','IN_STOCK',1),(8,'Tiger Beer',0.50,9.99,2,'Singapore- American-Style Lager- This pale golden, smooth tasting brew comes from a natural recipe, no additives, complete with malt barley from Australia and Europe and hops from Germany. Even the yeast is a unique strain cultured in Holland.','IN_STOCK',1),(9,'STELLA ARTOIS BTL 22.4OZ',0.50,3.99,2,'Stella Artois is one of the worlds best-selling beers and is enjoyed in more than 80 countries. Its full, characteristic flavour and high quality is assured through a superior brewing process and by using the finest ingredients available. The UK version is brewed, filtered and kegged at Samlesbury. Batches are sent to Magor for pasteurising before being canned or bottled.','SOLD_OUT',1),(10,'Suprema Beer',0.50,6.99,2,'El Salvador- American-Style Lager- This beer has a very nice aroma of pale malt and grassy hops. It is pale gold in color with a thin white head. It is very balanced and has a nice noble hop bite in the finish.','IN_STOCK',1),(11,'Moretti Beer',0.50,8.99,2,'Italy- Euro Pale Lager- This is produced using a traditional process that has remained almost unchanged since 1859. It is golden colored with nice aromas of malt and hops with a slight bitter character on the finish.','IN_STOCK',1),(12,'Lucky Beer',0.75,9.99,2,'China- Euro Pale Lager- Pale golden color. Light aromas of hops with nuances of honey & malt, which pre-announces the transcending flavours across your palate with a silky easy finish.','IN_STOCK',1),(13,'Moretti Beer',0.50,16.99,2,'Italy- Euro Pale Lager- This is produced using a traditional process that has remained almost unchanged since 1859. It is golden colored with nice aromas of malt and hops with a slight bitter character on the finish.','IN_STOCK',1),(14,'Wells Banana Bread Beer',0.50,4.99,2,'England- Fruit Beer- This dark golden colored ale hugs the palate with great intensity. Its malty aroma is complemented by the gentle nose of Banana. Its flavor unfolds with a sensual sparkle and a smart crispness, which balances its aroma perfectly.','IN_STOCK',1),(15,'Dr. Jekyll\'s Beer Belly',0.50,4.99,2,'California- Kolsch- Pours a pale yellow. Brewed with organic Perle and Cascade hops, and organic malts. Includes acai berry, maqui berry, green tea, green coffee bean, raspberry keytone, and grapefruit fiber. Flavors hint of raspberry, grapefruit and aromatic cardamom and coriander.','IN_STOCK',1),(16,'Korbel Brandy',0.75,11.99,4,'California- Created from the finest California grapes. Butterscotch aromas are as rich as the smooth caramel flavors. On the palate there is slight spice and nuttiness that smooth out into a medium length finish.','SOLD_OUT',1),(17,'E & J Brandy XO',0.75,10.99,4,'California- Carefully aged in premium oak barrels for more than 7 years, the result is a brandy of unparalleled quality, character and taste. Barrel aging brings out notes of sweet vanilla, creamy toffee, brown sugar, and soft maple with a hint of toasted oak. Bold and complex finish.','IN_STOCK',1),(18,'E & J Brandy VSOP',0.75,9.99,4,'USA- Pressed from fine varietal grapes, E & J is distilled to concentrate the most desirable flavors. VSOP is charcoal filtered to create a most rich, rare and remarkable brandy, and mellowed in select American White Oak casks to highlight the delicate grape flavors.','IN_STOCK',1),(19,'1889 Royal Brandy',0.75,6.49,4,'California - Crafted from the finest grapes in the honor of Italian nobility, 1889 Royal Brandy is burnished copper in color with aromas of caramel. A soft entry and a dry light body that gives way to a smooth yet warm finish. Enjoy straight, on the rocks, or with your favorite mixer','IN_STOCK',1),(20,'Christian Bros Brandy',0.75,7.49,4,'USA - Distilled using pot still production method used with fine Cognacs and aged 2 to 6 years in oak barrels. Barrels are then carefully blended to create unequaled smoothness and full-body taste in brandy that is imbued with layers of well-balanced fruit and a warm, lingering finish.','IN_STOCK',1),(21,'Champagne Hediard Euro 2016',75.00,31.49,10,'Grand Cru Brut, Champagne, France - Official Champagne of soccer Euro 2016. This Champagne has a fruity nose and clean palate, with flavors of yellow fruit like peach, and mandarin. Pairing with Chaource cheese or apple pie, with friends watching a soccer match.','IN_STOCK',1),(22,'Champagne Lallier Ouvrage',0.75,85.49,10,'Wine Enthusiast -Champagne, France \"Aged for five years on its lees before disgorgement, this prestige cuvee is ripe and beautifully mature. It has a rich feel, moving towards toastiness while retaining tangy citrus and white fruit flavors. This generous wine is ready to drink.\"','IN_STOCK',1),(23,'Veuve Clicquot Brut NV',0.75,40.97,10,'Burghound-France - \"This remains quite fresh with its cool nose of apple, citrus and exuberant yeast elements that continue onto the crisp, intense and energetic flavors that terminate in a saline-inflected, wonderfully complex and beautifully well-balanced finale.\"','IN_STOCK',1),(24,'Dom Perignon',0.75,149.97,10,'Champagne, France - Dom carries the flag for Champagne around the world, and it does so without being too strident or delicate. Everything is kept to a deceptively simple balance. The aroma is perfect, touches of light toastiness, broad yeastiness, and hints of a Cognac like richness.','IN_STOCK',1),(25,'De Margerie Grand Cru Brut',0.75,31.49,10,'Beverage Dynamics-Champagne, France- Simply gorgeous! A crisp, clean blend of 90% Pinot Noir and 10% Chardonnay from the famed Bouzy region. Pale gold in color, with a nose of red fruit followed by a lively palate filled with pin-point bubbles that caress the palate into a long, refreshing finish.','SOLD_OUT',1),(26,'Hennessy VS',0.75,31.99,5,'Cognac, France- A blend of over 40 eaux de vies from the four premier growing regions of Cognac. Beautiful golden color with a fruity sweetness and a hint of vanilla in the finish. A classic.','IN_STOCK',1),(27,'Hennessy VS',1.75,69.99,5,'Cognac, France- A blend of over 40 eaux de vies from the four premier growing regions of Cognac. Beautiful golden color with a fruity sweetness and a hint of vanilla in the finish. A classic.','IN_STOCK',1),(28,'Remy Martin VSOP',0.75,36.99,5,'Cognac, France- The world\'s first V.S.O.P Cognac Fine Champagne, Remy Martin V.S.O.P is a well-balanced and multi-layered cognac aged up to 14 years in French oak casks with vanilla, stone fruit and licorice notes.','IN_STOCK',1),(29,'Courvoisier VS Cognac',0.75,22.99,5,'Cognac, France- A blend of several crus aged between three and seven years, composed principally of Fins Bois with a balancing hand of Petite Champagne. The fusion of younger and older cognacs gives Courvoisier VS a fruity, delicate taste and a bouquet filled with ripe fruit.','SOLD_OUT',1),(30,'Martell Cordon Bleu',0.75,149.99,5,'Cognac, France- Bouquet is semisweet; several minutes of aeration unleashes much deeper scents of honeydew melon, cantaloupe, roasted almonds and apple butter. Palate is full bodied, generous and sweet; at midpalate, the flavor profile turns satiny. Extended, smoky and luscious finish.','IN_STOCK',1),(31,'D\'Usse Cognac',0.75,64.99,5,'France- Masterfully crafted at Chateau de Cognac, one of the oldest Cognac houses in France. Naturally aged to a beautifully bold blend with a rich bouquet of woody notes and a touch of cinnamon. Distinctively smooth flavors of spice, almond and cinnamon with dried fruit accents.','IN_STOCK',1),(32,'Tanqueray Gin',175.00,21.49,9,'United Kingdom- Crafted using a time honored recipe of only the highest quality grains, the purest water, the most select juniper berries, finest botanicals, and a unique quadruple-distillation process.','IN_STOCK',1),(33,'Hendricks Gin',0.75,26.99,9,'Scotland- Made in small batches with a fully restored 19th-centrury Carter-Head Still, which \"bathes\" the ingredients in vapors instead of boiling, producing a smoother gin. A unique blend of botanicals, including Bulgarian Rose petal and cucumber, create this extraordinary gin.','IN_STOCK',1),(34,'Bombay Sapphire',0.75,18.99,9,'England-The spirit is triple distilled, and the alcohol vapors are passed through bundles of the herbs and spices in order to gain flavor and aroma of almond, lemon peel, liquorice, juniper berries, orris root, angelica, coriander, cassia, cubeb, and grains of paradise.','IN_STOCK',1),(35,'Viniq Liqueur',0.75,18.99,7,'USA - When fused with Premium Vodka, Moscato, and Natural Fruit Flavors, this tasteless, odorless pearly color is what elevates Viniq to the complete package. With a sweet taste and a one-of-a-kind look, Viniq is a head turner. Serve alone or as a mixer. Just shake to shimmer.','IN_STOCK',1),(36,'Nero Walnut Liqueur',0.75,27.99,7,'Bulgaria - With a delicate, fragrant flavor, Nero Walnut Liqueur is a classic liqueur made from organic black walnuts Ideal served straight, on the rocks or chilled in the freezer. A wonderful addition to espresso coffee and cappuccino.','IN_STOCK',1),(37,'Luxardo Maraschino Liqueur',0.75,29.49,7,'Italy- Bright and clear, this Marasca cherry liqueur has been made in Padova since 1821. It features an earthy nose and a sweet, creamy wild berry flavor with spice. Long finish.','IN_STOCK',1),(38,'Malahat Rum',0.75,31.99,6,'CA, USA - Our Rum is the perfect base for a wide range of craft cocktails. The nose is sweet with a hint of the tropics. It is smooth, clean, and crisp from the start to the finish.','IN_STOCK',1),(39,'Plantation Grande Reserve Rum',0.75,21.99,6,'Wine Enthusiast -Barbados- A honey-colored spirit finished in old Cognac barrels. The end result is luxe but light, yielding caramel and orange peel aromas, as well as loads of sweet honey and creamy caramel on the palate, accented with a bright touch of lemon. 94pts. Wine Enthusiast. Best Buy.','SOLD_OUT',1),(40,'Gosling\'s Black Seal Rum',0.75,18.49,6,'Bermuda- A rich dark rum with full and intricate flavors, it is well balanced and nuanced with butterscotch, vanilla and caramel notes. Fragrant, with herbal sharpness, and unusually complex.','IN_STOCK',1),(41,'Appleton Special Rum',1.75,32.99,6,'Two rums aged separately and hand blended to create this medium bodied golden rum. Strong nose but delicate, smooth, fruity flavor allowing for a perfect combination with cola or component to your favorite long drink.','IN_STOCK',1),(42,'Diplomatico Rum Reserva',0.75,24.99,6,'Venezuela- Distilled in copper pot stills, then aged in oak casks and bottled at 12 years of age. A very dark rum with caramelized molasses, ripe fruit and nut flavors. This rum should be sipped slowly.','IN_STOCK',1),(43,'Jack Daniels Black',1.75,29.79,1,'Tennessee- Sour mash whiskey made in Tennessee from natural corn, rye, barley malt and then charcoal filtered. Sweet with caramel, vanilla and charred wood. Pure, clean, and smooth. Classic. Enjoy on the rocks or in mixed drinks.','IN_STOCK',1),(44,'Crown Royal',1.75,28.99,1,'Canada- A smooth blend of 50 distinct, full-bodied whiskies matured in white oak barrels. The taste is enhanced by a rich, lingering finish with hints of oak and vanilla. Approachable and unpretentious. Enjoy neat, with ice, or in cola.','IN_STOCK',1),(45,'Johnnie Walker Black',1.75,46.99,1,'Scotland- The original Walker family blend, handcrafted from as many as 40 of the finest Scotch whiskies aged a minimum of 12 years, for a smooth and robust blend. Rich smoky malt, peat and sherry fruit character deliver a satisfyingly complex flavor on the long, lingering finish.','IN_STOCK',1),(46,'Jameson Irish Whiskey',1.75,39.99,1,'Wine Enthusiast -Ireland- A pot still whiskey blended of light and medium flavored whiskies that were triple distilled for exceptional smoothness. Matured in Bourbon and Sherry casks to produce a crisp, clean, mellow taste with sweet, spicy, vanilla tones, and a subtle finish. Very smooth and versatile.','IN_STOCK',1),(47,'Jim Beam Bourbon Whiskey',1.75,18.99,1,'Kentucky- The world\'s number one bourbon. Jim Beam was founded in 1795 and has been operated by one family for seven generations. We\'ve always created bourbon our way, aging it twice as long as the law requires.','IN_STOCK',1),(48,'Carson Ridge Cabernet Paso Robles',0.75,10.79,8,'Paso Robles, Central Coast, CA- This medium to full-bodied Cabernet is balanced and richly colored, with just a touch of oak to complement the ripe berry aromas. Enjoy with full flavored dishes and your favorite cheeses.','IN_STOCK',1),(49,'Courtney Benham Chardonnay',0.75,14.39,8,'Santa Barbara, Central Coast, CA- This wine offers a smooth, creamy texture and lots of pear, tropical fruit, guava, melon and fig. The flavors are rich and exotic. Balanced oak and complex on the finish.','IN_STOCK',1),(50,'DeBeaune Connoisseur White',1.50,13.49,8,'France- Here\'s an easy-drinking, fruit-forward white that offers soft aromas of peaches and honeysuckle on the nose, while flavors of apple and apricot explode on the palate.','IN_STOCK',1),(51,'Oak Grove Chardonnay Reserve',1.50,14.39,8,'California- Full of ripe fruit flavors, with a smooth, lengthy finish. Oak Grove believes that the best wines are made in the vineyard, so they seek out the finest grapes from the cool appellations of California. A company-wide top seller.','IN_STOCK',1),(52,'1858 Cabernet Sauvignon Napa Valley',0.75,49.49,8,'Napa, CA - This hand crafted Napa Cabernet is a powerhouse in flavor and texture. Featuring luscious, ripe blackberry flavors with undertones of dark chocolate and toasted oak. The initial surge of raspberry is stunning but almost outdone by the regal tannic finish.','IN_STOCK',1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(100) NOT NULL,
  `password` varchar(256) NOT NULL,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `birthDate` date NOT NULL,
  `address` varchar(300) DEFAULT NULL,
  `phone` varchar(45) NOT NULL,
  `enabled` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `user_email_uindex` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'admin@gmail.com','$2a$10$tEeTUFxFd095z7jnZt6H/e/4/rl7iEo4me2dZ8ZdoQhzh7M4xGeuS','Admin','Adminych','1990-09-16','Matrix','0100110111',1),(11,'john@gmail.com','$2a$10$RmG4NExBhTx8mVCA2O3iWuxAd2CEZ1mgk0dCSMDw8pHNrBgMZRbLG','John','Depp','2015-07-14','Boston, Ukrainian st., 28','4769033212113',1),(12,'megan@gmail.com','$2a$10$zygY8oqE2AFMyy7ilOmDfepbnXez0QtJgM48xlwWX3ERTeGPdv07K','Megan','Fox','1986-05-15','Albion Street, Surry Hills, 64a Sydney, Australi','046236548234',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authority` varchar(45) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_user_role_user1_idx` (`user_id`),
  CONSTRAINT `FKcnrjdc801vcdj8n017nlriv61` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `fk_user_role_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (5,'ADMIN',8),(6,'USER',11),(7,'USER',12);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-03 15:19:02
