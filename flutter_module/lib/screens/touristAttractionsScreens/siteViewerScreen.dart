import 'package:flutter/material.dart';
import 'package:flutter_module/packages/bottomBarView.dart';
import 'package:flutter_module/screens/capturingScreen/captureingScreenView.dart';
import 'package:flutter_module/screens/touristAttractionsScreens/placesScreenView.dart';

class SiteViwer extends StatelessWidget {
  final int siteId;
  SiteViwer({@required this.siteId});
  @override
  Widget build(BuildContext context) {
    // provider for getting data
    return SiteViwerScreen(
      siteId: siteId,
    );
  }
}

class SiteViwerScreen extends StatelessWidget {
  double screenWidth, screenHeight;
  final int siteId;
  String name, image, tages, city, cover;
  SiteViwerScreen({@required this.siteId});
  @override
  Widget build(BuildContext context) {
    screenHeight =
        MediaQuery.of(context).size.height - MediaQuery.of(context).padding.top;
    screenWidth = MediaQuery.of(context).size.width;
    name = places[siteId]["name"];
    tages = places[siteId]["tages"];
    city = places[siteId]["city"];
    cover = places[siteId]["cover"];
    return SafeArea(
      child: Scaffold(
        body: Container(
          child: Stack(
            children: [
              // the background image
              Image.asset(
                cover,
                fit: BoxFit.cover,
                width: screenWidth,
                height: screenHeight,
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
                              "Lorem ipsum dolor sit amet, consctetur adipiscing elit, Ornare leo non mollis id cursus....",
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
                                // TODO: go to MoreDetailsScreen
                                // Navigator.of(context).push(MaterialPageRoute(
                                //     builder: (context) => MoreDetailsScreen()));
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
                      // for now will open it in a snake bar
                      SnackBar snackBar = SnackBar(
                        content: Text(
                          'Go To My Translation',
                          textAlign: TextAlign.center,
                        ),
                        duration: Duration(seconds: 2),
                      );
                      ScaffoldMessenger.of(context).showSnackBar(snackBar);
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
