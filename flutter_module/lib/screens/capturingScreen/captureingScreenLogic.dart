/// here i will use 3 libraries :
///
/// 1- camera
/// Provides tools to work with the cameras on the device.
/// TODO: need ios configration
///
/// 2-path_provider
/// Finds the correct paths to store images.
///
/// 3-path
/// Creates paths that work on any platform.
///
import 'package:camera/camera.dart' as cameraPlugin;
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:flutter_module/core/models/capatureAndTranslatingModels/capturingScreenModel.dart';
import 'package:flutter_module/screens/capturingScreen/loadingScreen.dart';
import 'package:image_picker/image_picker.dart';

List<cameraPlugin.CameraDescription> cameras;

class CaptureingScreenLogic {
  CapturingScreenModel capturingScreenModel;
  BuildContext parentContext;
  Future _getAvailableCameras() async {
    cameras = await cameraPlugin.availableCameras();
    print(cameras);
  }

  Future _setcontroller() async {
    capturingScreenModel.cameraController = cameraPlugin.CameraController(
        cameras.first, cameraPlugin.ResolutionPreset.max,
        enableAudio: false);
    try {
      await capturingScreenModel.cameraController.initialize();
      await capturingScreenModel.cameraController
          .setFlashMode(cameraPlugin.FlashMode.off);
    } catch (e) {
      print("there is somthing went wrong : $e  and thets it");
    }
  }

  void loadDataFirstTime() async {
    await _getAvailableCameras();
    await _setcontroller();
    print("it is ok till now");
    capturingScreenModel.dataIsLoaded();
  }

  Future captureAnImage() async {
    try {
      await capturingScreenModel.cameraController.takePicture().then((image) {
        Navigator.of(parentContext).push(MaterialPageRoute(
            builder: (context) => LoadingScreen(
                  image: image,
                )));
        capturingScreenModel.cameraController.dispose();
      });
    } catch (e) {
      print("there is somthing went wrong : $e  and thets it");
    }
  }

  Future getImageFromGallery() async {
    final picker = ImagePicker();
    PickedFile file = await picker.getImage(source: ImageSource.gallery);
    if (file != null) {
      cameraPlugin.XFile image = cameraPlugin.XFile(file.path);
      Navigator.of(parentContext).push(MaterialPageRoute(
          builder: (context) => LoadingScreen(
                image: image,
              )));
      capturingScreenModel.cameraController.dispose();
    }
  }
}
