import 'package:flutter/material.dart';
import 'package:camera/camera.dart';
import 'package:flutter_module/core/models/capatureAndTranslatingModels/capturingScreenModel.dart';
import 'package:flutter_module/packages/bottomBarView.dart';
import 'package:flutter_module/screens/capturingScreen/captureingScreenLogic.dart';
import 'package:flutter_module/screens/touristAttractionsScreens/placesScreenView.dart';
import 'package:provider/provider.dart';

class CaptureingScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => CapturingScreenModel(),
      child: CaptureingScreenView(),
    );
  }
}

class CaptureingScreenView extends StatelessWidget {
  CaptureingScreenLogic captureingScreenLogic = CaptureingScreenLogic();
  double screenWidth, screenHeight;
  @override
  Widget build(BuildContext context) {
    screenWidth = MediaQuery.of(context).size.width;
    screenHeight = MediaQuery.of(context).size.height;
    captureingScreenLogic.capturingScreenModel =
        Provider.of<CapturingScreenModel>(context, listen: false);
    captureingScreenLogic.parentContext = context;
    captureingScreenLogic.loadDataFirstTime();
    return SafeArea(
      child: Scaffold(
          body: Container(
        child: Stack(
          children: [
            Consumer<CapturingScreenModel>(
              builder: (context, CapturingScreenModel csm, child) {
                return captureingScreenLogic.capturingScreenModel.isDataLoaded
                    ? Container(
                        width: screenWidth,
                        height: screenHeight * 0.856 - 5,
                        color: Colors.yellow,
                        child: CameraPreview(
                          csm.cameraController,
                          // child: Align(
                          //     alignment: Alignment.bottomCenter,
                          //     child: Container(
                          //       width: screenWidth,
                          //       height: screenHeight * 0.15,
                          //       decoration: BoxDecoration(),
                          //       child: Center(
                          //           child: Container(
                          //         width: screenHeight * 0.1,
                          //         height: screenHeight * 0.1,
                          //         decoration: BoxDecoration(
                          //             shape: BoxShape.circle,
                          //             color: Colors.yellow[700],
                          //             border: Border.all(
                          //                 color: Colors.white,
                          //                 width: screenHeight * 0.005)),
                          //       )),
                          //     )),
                        ))
                    : Container(
                        child: Center(
                          child: Theme(
                              data: ThemeData(
                                // FIXME: put the right color
                                accentColor: Colors.yellow[700],
                              ),
                              child: CircularProgressIndicator()),
                        ),
                      );
              },
            ),
            Align(
              alignment: Alignment.bottomCenter,
              child: BottomNavigationBarPainted(
                icons: [
                  "assets/icons/bottomNavigationBar/home.png",
                  "assets/icons/bottomNavigationBar/photo-camera.png",
                  "assets/icons/bottomNavigationBar/add-from-gallery.png"
                ],
                texts: ["Explore", "Gallery"],
                iconsColors: [
                  Color(0xffb6b7b7),
                  Colors.white,
                  Color(0xffffd600)
                ],
                functions: [
                  () {
                    // back to home screen
                    // for now will open it in a snake bar
                    Navigator.of(context).push(MaterialPageRoute(
                        builder: (context) => PlacesScreen()));
                  },
                  () async {
                    // capture an image
                    print("capture an image");
                    await captureingScreenLogic.captureAnImage();
                  },
                  () async {
                    // getImage from gallery
                    await captureingScreenLogic.getImageFromGallery();
                  },
                ],
              ),
            ),
          ],
        ),
      )),
    );
  }
}
