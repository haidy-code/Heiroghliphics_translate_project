import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:flutter_module/packages/bottomBarView.dart';
import 'package:flutter_module/screens/capturingScreen/captureingScreenView.dart';
import 'package:flutter_module/screens/touristAttractionsScreens/moreDetailsScreen.dart';
import 'package:flutter_module/screens/touristAttractionsScreens/placesScreenView.dart';

class SiteViwer extends StatelessWidget {
  final Map siteData;
  SiteViwer({@required this.siteData});
  @override
  Widget build(BuildContext context) {
    // provider for getting data
    return SiteViwerScreen(
      siteData: siteData,
    );
  }
}

class SiteViwerScreen extends StatelessWidget {
  double screenWidth, screenHeight;
  final Map siteData;
  String name, tages, city, cover, description;
  SiteViwerScreen({@required this.siteData}) {
    name = siteData["siteName"];
    tages = "#${siteData["governorate"]} #${siteData["type"]}";
    city = siteData["governorate"];
    cover = siteData["site_cover"];
    description = siteData["description"].substring(0, 88);
  }
  @override
  Widget build(BuildContext context) {
    screenHeight =
        MediaQuery.of(context).size.height - MediaQuery.of(context).padding.top;
    screenWidth = MediaQuery.of(context).size.width;
    return SafeArea(
      child: Scaffold(
        body: Container(
          child: Stack(
            children: [
              // the background image
              Image.network(
                "https://hieroglyphics-recognition-api.herokuapp.com/getHistoricalSitesImage/$cover",
                width: screenWidth,
                height: screenHeight,
                fit: BoxFit.cover,
              ),
              // the filter on it
              Container(
                width: screenWidth,
                height: screenHeight,
                color: Color(0x66000000),
              ),
              // go back
              Positioned(
                  top: screenHeight * 0.03,
                  left: screenWidth * 0.05,
                  child: InkWell(
                    onTap: () {
                      Navigator.of(context).pop();
                    },
                    child: Row(
                      children: [
                        Icon(
                          Icons.arrow_back_ios,
                          color: Colors.white,
                          size: screenHeight * 0.03,
                        ),
                        Text(
                          name,
                          style: TextStyle(
                              color: Colors.white,
                              fontSize: screenHeight * 0.025,
                              fontWeight: FontWeight.bold),
                        )
                      ],
                    ),
                  )),
              // the details
              Positioned(
                top: screenHeight * 0.45,
                width: screenWidth,
                child: Center(
                  child: Container(
                    width: screenWidth,
                    height: screenHeight * 0.35,
                    child: Stack(
                      children: [
                        // title
                        Positioned(
                          left: screenWidth * 0.08,
                          child: Text(
                            name,
                            style: TextStyle(
                                fontSize: screenHeight * 0.035,
                                color: Color(0xffffd600),
                                fontWeight: FontWeight.bold),
                          ),
                        ),
                        // description
                        Positioned(
                          top: screenHeight * 0.07,
                          left: screenWidth * 0.08,
                          child: Container(
                            height: screenHeight * 0.09,
                            width: screenWidth * 0.82,
                            child: Text(
                              "$description ....",
                              style: TextStyle(
                                  fontSize: screenHeight * 0.023,
                                  color: Colors.white,
                                  fontWeight: FontWeight.w400),
                            ),
                          ),
                        ),
                        // tages
                        Positioned(
                          top: screenHeight * 0.17,
                          left: screenWidth * 0.08,
                          child: Text(
                            tages,
                            style: TextStyle(
                                fontSize: screenHeight * 0.018,
                                color: Colors.yellow.shade600,
                                fontWeight: FontWeight.bold),
                          ),
                        ),
                        // city
                        Positioned(
                          top: screenHeight * 0.17,
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
                        // see more
                        Positioned(
                          top: screenHeight * 0.26,
                          width: screenWidth,
                          child: Center(
                            child: InkWell(
                              onTap: () {
                                // go to MoreDetailsScreen
                                Navigator.of(context).push(MaterialPageRoute(
                                    builder: (context) => MoreDetailsScreen(
                                          siteData: siteData,
                                        )));
                              },
                              child: Column(
                                crossAxisAlignment: CrossAxisAlignment.center,
                                children: [
                                  Text(
                                    "More Details",
                                    textAlign: TextAlign.center,
                                    style: TextStyle(
                                      fontSize: screenHeight * 0.022,
                                      color: Color(0xffffd600),
                                    ),
                                  ),
                                  Icon(
                                    Icons.expand_more,
                                    color: Color(0xffffd600),
                                    size: screenHeight * 0.03,
                                  ),
                                ],
                              ),
                            ),
                          ),
                        ),
                      ],
                    ),
                  ),
                ),
              ),
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
                      // back to home screen
                      Navigator.of(context).pop();
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
          ),
        ),
      ),
    );
  }
}
