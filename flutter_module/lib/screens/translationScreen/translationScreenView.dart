import 'dart:io';
import 'package:flutter/material.dart';
import 'package:camera/camera.dart' as cameraPlugin;
import 'package:flutter/services.dart';
import 'package:flutter_module/packages/bottomBarView.dart';
import 'package:path_provider/path_provider.dart' as path_provider;

class TranslationScreen extends StatelessWidget {
  final cameraPlugin.XFile image;
  TranslationScreen({this.image});
  @override
  Widget build(BuildContext context) {
    return TranslationScreenView(
      image: image,
    );
  }
}

class TranslationScreenView extends StatelessWidget {
  double screenWidth, screenHeight, screenTopPadding;
  cameraPlugin.XFile image;
  TranslationScreenView({this.image});
  @override
  Widget build(BuildContext context) {
    screenWidth = MediaQuery.of(context).size.width;
    screenHeight = MediaQuery.of(context).size.height;
    screenTopPadding = MediaQuery.of(context).viewPadding.top;
    return Scaffold(
      body: Container(
        color: Colors.white,
        child: Stack(
          children: [
            //image
            Positioned(
                top: 0,
                height: screenHeight * 0.37,
                width: screenWidth,
                child: Container(
                    color: Colors.white,
                    child: Image.asset("assets/images/tempImage.png",
                        fit: BoxFit.fill)
                    // Image.file(File(image.path), fit: BoxFit.fill)
                    )),
            // translation
            Positioned(
                top: screenHeight * 0.3 + screenTopPadding,
                child: Container(
                  width: screenWidth,
                  height: screenHeight * 0.7,
                  decoration: BoxDecoration(
                      color: Colors.white,
                      boxShadow: [
                        BoxShadow(
                          color: Colors.black.withOpacity(0.2),
                          spreadRadius: 3,
                          blurRadius: 5,
                          offset: Offset(0, 3), // changes position of shadow
                        ),
                      ],
                      borderRadius: BorderRadius.only(
                          topLeft: Radius.circular(screenHeight * 0.07),
                          topRight: Radius.circular(screenHeight * 0.07))),
                  child: ClipRRect(
                    borderRadius: BorderRadius.only(
                        topLeft: Radius.circular(screenHeight * 0.07),
                        topRight: Radius.circular(screenHeight * 0.07)),
                    child: Theme(
                      data: ThemeData(
                        accentColor: Color(0x33fbc02d),
                      ),
                      child: SingleChildScrollView(
                        child: Container(
                          width: screenWidth,
                          height: screenHeight * 0.8,
                          child: Stack(
                            children: [
                              //Title
                              Positioned(
                                  //parent height: screenHeight * 0.7,
                                  top: screenHeight * 0.04,
                                  left: screenHeight * 0.04,
                                  child: Text(
                                    "Heiroglyphic Translation",
                                    style: TextStyle(
                                        fontSize: screenHeight * 0.026,
                                        fontWeight: FontWeight.w400),
                                  )),
                              // translation Title
                              Positioned(
                                  //parent height: screenHeight * 0.7,
                                  top: screenHeight * 0.12,
                                  left: screenHeight * 0.04,
                                  child: Text(
                                    "Translation",
                                    style: TextStyle(
                                        fontSize: screenHeight * 0.022,
                                        fontWeight: FontWeight.w600),
                                  )),
                              // translation text
                              Positioned(
                                  //parent height: screenHeight * 0.7,
                                  top: screenHeight * 0.16,
                                  left: screenHeight * 0.04,
                                  child: Container(
                                    width: screenWidth - screenHeight * 0.08,
                                    child: Text(
                                      "This is a temp translation till a proper image passed to the model",
                                      style: TextStyle(
                                          fontSize: screenHeight * 0.022,
                                          fontWeight: FontWeight.w300),
                                    ),
                                  )),
                              // Analyzed image Title
                              Positioned(
                                  //parent height: screenHeight * 0.7,
                                  top: screenHeight * 0.25,
                                  left: screenHeight * 0.04,
                                  child: Text(
                                    "Analyzed image",
                                    style: TextStyle(
                                        fontSize: screenHeight * 0.022,
                                        fontWeight: FontWeight.w600),
                                  )),
                              // Analyzed image content
                              Positioned(
                                  top: screenHeight * 0.3,
                                  left: screenHeight * 0.04,
                                  child: Container(
                                      child: Row(
                                    children: [
                                      Container(
                                          padding: EdgeInsets.only(right: 16),
                                          child: Image.asset(
                                            "assets/icons/icon1.png",
                                          )),
                                      Container(
                                          child: Image.asset(
                                        "assets/icons/icon2.png",
                                      )),
                                    ],
                                  ))),
                              // extra draw
                              Positioned(
                                  top: screenHeight * 0.4,
                                  child: Container(
                                    padding: EdgeInsets.only(
                                        top: 20, bottom: 20, right: 10),
                                    width: screenHeight * 0.1,
                                    height: screenHeight * 0.25,
                                    decoration: BoxDecoration(
                                      color: Color(0xfffbc02d),
                                      borderRadius: BorderRadius.only(
                                          bottomRight: Radius.circular(
                                              screenHeight * 0.07),
                                          topRight: Radius.circular(
                                              screenHeight * 0.07)),
                                    ),
                                    child:
                                        Image.asset("assets/icons/icon3.png"),
                                  ))
                            ],
                          ),
                        ),
                      ),
                    ),
                  ),
                )),
            // saving button
            Positioned(
                top: screenHeight * 0.28,
                right: screenWidth * 0.05,
                child: InkWell(
                  onTap: () async {
                    final directory =
                        await path_provider.getExternalStorageDirectory();
                    String _localPath = directory.path;
                    File myfile = File('$_localPath/data.json');
                    await myfile.writeAsString({
                      "symbolsList": [
                        ["A15", "E1", "C23"]
                      ],
                      "translationList": ["hello panda"],
                      "imageName":"the name of the image",
                    }.toString());
                    SystemNavigator.pop();
                  },
                  child: Image.asset(
                    'assets/icons/icon.png',
                    width: screenHeight * 0.1,
                    height: screenHeight * 0.1,
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
                    print("go to capture");
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
