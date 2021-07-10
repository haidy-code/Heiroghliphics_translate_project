import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_module/core/models/placesScreen/placesScreenModel.dart';
import 'package:flutter_module/packages/bottomBarView.dart';
import 'package:flutter_module/screens/capturingScreen/captureingScreenView.dart';
import 'package:flutter_module/screens/touristAttractionsScreens/placesScreenLogic.dart';
import 'package:flutter_module/screens/touristAttractionsScreens/siteViewerScreen.dart';
import 'package:provider/provider.dart';

final List<Map<String, dynamic>> places = [
  {
    "name": "Pyramids Of Giza",
    "image": "assets/images/touristAttractionsScreens/image1.png",
    "cover": "assets/images/touristAttractionsScreens/image4.png",
    "tages": "# Giza  # Culture  #Historical",
    "city": "Giza",
    "siteId": 0
  },
  {
    "name": "Abu Simple Temple",
    "image": "assets/images/touristAttractionsScreens/image2.png",
    "cover": "assets/images/touristAttractionsScreens/image5.jpg",
    "tages": "# Aswan  # Culture  #Historical",
    "city": "Aswan",
    "siteId": 1
  },
  {
    "name": "Philae Temple",
    "image": "assets/images/touristAttractionsScreens/image3.png",
    "cover": "assets/images/touristAttractionsScreens/image6.jpeg",
    "tages": "# Aswan  # Culture  #Historical",
    "city": "Aswan",
    "siteId": 2
  }
];

class PlacesScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: ChangeNotifierProvider(
      create: (context) => PlacesScreenModel(),
      child: PlacesScreenView(),
    ));
  }
}

class PlacesScreenView extends StatelessWidget {
  double screenWidth, screenHeight;
  PlacesScreenLogic placesScreenLogic;
  @override
  Widget build(BuildContext context) {
    screenWidth = MediaQuery.of(context).size.width;
    screenHeight =
        MediaQuery.of(context).size.height - MediaQuery.of(context).padding.top;
    return Scaffold(
      body: Container(child: Consumer<PlacesScreenModel>(
          builder: (context, PlacesScreenModel psm, child) {
        if (psm.isTheDataLoadedOk &&
            psm.listOfData != null &&
            !(psm.listOfData.isEmpty))
          return Stack(
            children: [
              // the appbar
              Positioned(
                  top: MediaQuery.of(context).padding.top,
                  width: screenWidth,
                  child: Center(
                    child: Container(
                      width: screenWidth * 0.85,
                      height: screenHeight * 0.17,
                      child: Column(
                        mainAxisAlignment: MainAxisAlignment.spaceBetween,
                        children: [
                          // the title
                          Padding(
                            padding: const EdgeInsets.all(1),
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.spaceBetween,
                              children: [
                                // the title
                                Text(
                                  "Explore",
                                  style: TextStyle(
                                      fontSize: screenHeight * 0.04,
                                      color: Colors.grey.shade600,
                                      fontWeight: FontWeight.w400),
                                ),
                                // the sortIcon
                                Image.asset(
                                  "assets/icons/touristAttractionsScreens/filter.png",
                                  width: screenHeight * 0.08,
                                  height: screenHeight * 0.08,
                                  color: Colors.yellow,
                                )
                              ],
                            ),
                          ),

                          // the search bar
                          Container(
                            height: screenHeight * 0.07,
                            width: screenWidth * 0.85,
                            decoration: BoxDecoration(
                                color: Color(0x88dadada),
                                borderRadius: BorderRadius.all(
                                    Radius.circular(screenHeight * 0.07))),
                            child: Row(
                              mainAxisAlignment: MainAxisAlignment.start,
                              crossAxisAlignment: CrossAxisAlignment.center,
                              children: [
                                // search icon
                                IconButton(
                                    icon: Icon(
                                      Icons.search,
                                      color: Color(0xff4a4a4a),
                                    ),
                                    onPressed: () {
                                      print("i am going tp search");
                                    }),
                                // edit text field
                                Container(
                                  width: screenWidth * 0.55,
                                  child: TextField(
                                    onChanged: (input) {},
                                    keyboardType: TextInputType.text,
                                    style: TextStyle(),
                                    decoration: InputDecoration(
                                      border: InputBorder.none,
                                      hintText: "Try \"Luxor\"",
                                      hintStyle: TextStyle(
                                        color: Color(0xffc2c2c2),
                                        fontFamily: "Cairo",
                                      ),
                                    ),
                                    cursorColor: Color(0xff4a4a4a),
                                  ),
                                ),
                              ],
                            ),
                          )
                        ],
                      ),
                    ),
                  )),
              // the screenbody
              Positioned(
                  top: screenHeight * 0.22,
                  width: screenWidth,
                  height: screenHeight * 0.63,
                  child: Center(
                    child: Container(
                      width: screenWidth * 0.9,
                      height: screenHeight * 0.63,
                      padding: EdgeInsets.only(top: 10),
                      child: Theme(
                        data: ThemeData(accentColor: Colors.white),
                        child: SingleChildScrollView(
                          child: Column(
                            children: [
                              ...List.generate(
                                  psm.listOfData.length,
                                  (index) => TouristAttractionOuter(
                                        siteData: psm.listOfData[index],
                                      ))
                            ],
                          ),
                        ),
                      ),
                    ),
                  )),
              // the bottom navigationbar
              Positioned(
                bottom: 0,
                child: BottomNavigationBarPainted(
                  icons: [
                    "assets/icons/bottomNavigationBar/home.png",
                    "assets/icons/bottomNavigationBar/photo-camera.png",
                    "assets/icons/bottomNavigationBar/add-bookmark.png"
                  ],
                  texts: ["Explore", "My Translation"],
                  iconsColors: [
                    Color(0xffffd600),
                    Colors.white,
                    Color(0xffb6b7b7)
                  ],
                  functions: [
                    () {
                      // home screen
                      print("i am already here");
                    },
                    () async {
                      // capture an image
                      Navigator.of(context).push(MaterialPageRoute(
                          builder: (context) => CaptureingScreen()));
                    },
                    () async {
                      // go to My Translation
                      SystemNavigator.pop();
                    },
                  ],
                ),
              )
            ],
          );
        else {
          placesScreenLogic = PlacesScreenLogic(psm: psm);
          placesScreenLogic.getNeededData();
          return Container(
            child: Center(
              child: Theme(
                  data: ThemeData(
                    accentColor: Colors.yellow[700],
                  ),
                  child: CircularProgressIndicator()),
            ),
          );
        }
      })),
    );
  }
}

class TouristAttractionOuter extends StatelessWidget {
  double screenWidth, screenHeight;
  final Map siteData;
  String name, image, tages, city;
  int siteId;
  TouristAttractionOuter({
    @required this.siteData,
  }) {
    image = siteData["site_image"];
    name = siteData["siteName"];
    tages ="#${siteData["governorate"]} #${siteData["type"]}";
    city = siteData["governorate"];
    siteId = siteData["site_id"];
  }
  @override
  Widget build(BuildContext context) {
    screenWidth = MediaQuery.of(context).size.width;
    screenHeight =
        MediaQuery.of(context).size.height - MediaQuery.of(context).padding.top;

    return Padding(
      padding: const EdgeInsets.only(bottom: 8.0),
      child: InkWell(
        onTap: () {
          print("i will go to site with is : $siteId");
          Navigator.of(context).push(MaterialPageRoute(
              builder: (context) => SiteViwer(
                    siteData: siteData,
                  )));
        },
        child: Container(
            width: screenWidth * 0.9,
            height: screenHeight * 0.2,
            // decoration: BoxDecoration(
            //   image: new DecorationImage(
            //     image: new ExactAssetImage(image),
            //     fit: BoxFit.cover,
            //   ),
            // ),
            child: Stack(
              children: [
                Image.network(
                  "https://hieroglyphics-recognition-api.herokuapp.com/getHistoricalSitesImage/$image",
                  width: screenWidth * 0.9,
                  height: screenHeight * 0.2,
                  fit: BoxFit.cover,
                ),
                Container(
                  width: screenWidth * 0.9,
                  height: screenHeight * 0.2,
                  decoration: BoxDecoration(
                      gradient: LinearGradient(
                    begin: const Alignment(0.0, 1.0),
                    end: const Alignment(0.0, 0.0),
                    colors: <Color>[
                      const Color(0xff4a4a4a),
                      const Color(0x004a4a4a)
                    ],
                  )),
                  child: Stack(
                    children: [
                      // title
                      Positioned(
                        top: screenHeight * 0.12,
                        left: screenWidth * 0.08,
                        child: Text(
                          name,
                          style: TextStyle(
                              fontSize: screenHeight * 0.026,
                              color: Colors.white,
                              fontWeight: FontWeight.bold),
                        ),
                      ),
                      // tages
                      Positioned(
                        top: screenHeight * 0.16,
                        left: screenWidth * 0.08,
                        child: Text(
                          tages,
                          style: TextStyle(
                              fontSize: screenHeight * 0.018,
                              color: Colors.yellow.shade600,
                              fontWeight: FontWeight.bold),
                        ),
                      ),
                      Positioned(
                        top: screenHeight * 0.16,
                        right: screenWidth * 0.08,
                        child: Row(
                          children: [
                            Icon(
                              Icons.location_pin,
                              color: Colors.yellow.shade600,
                              size: screenHeight * 0.02,
                            ),
                            Padding(
                              padding: const EdgeInsets.only(left: 5.0),
                              child: Text(
                                city,
                                style: TextStyle(
                                  fontSize: screenHeight * 0.018,
                                  color: Colors.white,
                                ),
                              ),
                            )
                          ],
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            )),
      ),
    );
  }
}
