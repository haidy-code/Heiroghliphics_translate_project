import 'package:flutter/material.dart';
import 'package:flutter_module/packages/bottomBarView.dart';
import 'package:flutter_module/screens/capturingScreen/captureingScreenView.dart';

const List<Map<String, dynamic>> places = [
  {
    "name": "Pyramids Of Giza",
    "image": "assets/images/touristAttractionsScreens/image1.png",
    "tages": "# Giza  # Culture  #Historical",
    "city": "Giza"
  },
  {
    "name": "Abu Simple Temple",
    "image": "assets/images/touristAttractionsScreens/image2.png",
    "tages": "# Aswan  # Culture  #Historical",
    "city": "Aswan"
  },
  {
    "name": "Philae Temple",
    "image": "assets/images/touristAttractionsScreens/image3.png",
    "tages": "# Aswan  # Culture  #Historical",
    "city": "Aswan"
  }
];

class PlacesScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(home: PlacesScreenView());
  }
}

class PlacesScreenView extends StatelessWidget {
  double screenWidth, screenHeight;
  @override
  Widget build(BuildContext context) {
    screenWidth = MediaQuery.of(context).size.width;
    screenHeight =
        MediaQuery.of(context).size.height - MediaQuery.of(context).padding.top;
    return Scaffold(
      body: Container(
        child: Stack(
          children: [
            // the appbar
            Positioned(
                top: MediaQuery.of(context).padding.top,
                width: screenWidth,
                child: Center(
                  child: Container(
                    width: screenWidth * 0.85,
                    height: screenHeight * 0.2,
                    // color: Colors.yellow,
                    child: Column(
                      children: [
                        Padding(
                          padding: const EdgeInsets.all(18),
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
                top: screenHeight * 0.25,
                width: screenWidth,
                height: screenHeight * 0.6,
                child: Center(
                  child: Container(
                    width: screenWidth * 0.9,
                    height: screenHeight * 0.6,
                    padding: EdgeInsets.only(top: 10),
                    child: Theme(
                      data: ThemeData(accentColor: Colors.white),
                      child: SingleChildScrollView(
                        child: Column(
                          children: [
                            ...List.generate(
                                places.length,
                                (index) => TouristAttractionOuter(
                                      image: places[index]["image"],
                                      name: places[index]["name"],
                                      tages: places[index]["tages"],
                                      city: places[index]["city"],
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
                    // back to home screen
                    // for now will open it in a snake bar
                    SnackBar snackBar = SnackBar(
                      content: Text(
                        'Go To HomeScreen',
                        textAlign: TextAlign.center,
                      ),
                      duration: Duration(seconds: 2),
                    );
                    ScaffoldMessenger.of(context).showSnackBar(snackBar);
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
    );
  }
}

class TouristAttractionOuter extends StatelessWidget {
  double screenWidth, screenHeight;
  final String name, image, tages, city;
  TouristAttractionOuter({
    @required this.city,
    @required this.name,
    @required this.tages,
    @required this.image,
  });
  @override
  Widget build(BuildContext context) {
    screenWidth = MediaQuery.of(context).size.width;
    screenHeight =
        MediaQuery.of(context).size.height - MediaQuery.of(context).padding.top;

    return Padding(
      padding: const EdgeInsets.only(bottom: 8.0),
      child: Container(
        width: screenWidth * 0.9,
        height: screenHeight * 0.2,
        decoration: BoxDecoration(
          image: new DecorationImage(
            image: new ExactAssetImage(image),
            fit: BoxFit.cover,
          ),
        ),
        child: Container(
          width: screenWidth * 0.9,
          height: screenHeight * 0.2,
          decoration: BoxDecoration(
              gradient: LinearGradient(
            begin: const Alignment(0.0, 1.0),
            end: const Alignment(0.0, 0.0),
            colors: <Color>[const Color(0xff4a4a4a), const Color(0x004a4a4a)],
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
      ),
    );
  }
}
