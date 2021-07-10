import 'package:flutter/material.dart';
import 'package:flutter_module/screens/touristAttractionsScreens/fullDescription.dart';

class MoreDetailsScreen extends StatelessWidget {
  final Map siteData;
  MoreDetailsScreen({@required this.siteData});
  @override
  Widget build(BuildContext context) {
    return MoreDetailsScreenView(
      siteData: siteData,
    );
  }
}

class MoreDetailsScreenView extends StatelessWidget {
  final Map siteData;
  double screenWidth, screenHeight;
  String image, name, description, governorate, location, famousFigure, type;
  List era, openingHours, prices;
  MoreDetailsScreenView({@required this.siteData}) {
    image = siteData["site_cover"];
    name = siteData["siteName"];
    description = siteData["description"].substring(0, 400);
    governorate = siteData["location"];
    type = siteData["type"];
    location = siteData["location"];
    famousFigure = siteData["famousFigure"];
    era = siteData["era"];
    openingHours = siteData["openingHours"];
    prices = siteData["prices"];
  }
  @override
  Widget build(BuildContext context) {
    screenWidth = MediaQuery.of(context).size.width;
    screenHeight =
        MediaQuery.of(context).size.height - MediaQuery.of(context).padding.top;
    return SafeArea(
      child: Scaffold(
        body: Container(
          child: Stack(
            children: [
              // image
              Positioned(
                  top: 0,
                  height: screenHeight * 0.6,
                  width: screenWidth,
                  child: Container(
                      color: Colors.white,
                      child: Image.network(
                        "https://hieroglyphics-recognition-api.herokuapp.com/getHistoricalSitesImage/$image",
                        fit: BoxFit.fill,
                      ))),
              // the rest of the screen
              // go back
              Positioned(
                  top: screenHeight * 0.03,
                  left: screenWidth * 0.05,
                  child: InkWell(
                      onTap: () {
                        Navigator.of(context).pop();
                      },
                      child: Center(
                        child: Icon(
                          Icons.arrow_back_ios,
                          color: Colors.black,
                          size: screenHeight * 0.035,
                        ),
                      ))),
              // the content
              Positioned(
                top: screenHeight * 0.35,
                width: screenWidth,
                child: ClipRRect(
                  borderRadius: BorderRadius.only(
                      topLeft: Radius.circular(screenHeight * 0.07),
                      topRight: Radius.circular(screenHeight * 0.07)),
                  child: Container(
                    width: screenWidth,
                    height: screenHeight * 0.65,
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
                    child: Theme(
                      data: ThemeData(
                        accentColor: Color(0x33fbc02d),
                      ),
                      child: SingleChildScrollView(
                          child: Container(
                        padding: EdgeInsets.only(
                            left: screenWidth * 0.05,
                            right: screenWidth * 0.05),
                        width: screenWidth,
                        height: screenHeight * 1.15,
                        child: Stack(children: [
                          // title
                          Positioned(
                            top: screenHeight * 0.04,
                            child: Text(
                              name,
                              textAlign: TextAlign.left,
                              style: TextStyle(
                                  fontSize: screenHeight * 0.03,
                                  color: Colors.black),
                            ),
                          ),
                          // location
                          Positioned(
                            top: screenHeight * 0.09,
                            child: Row(children: [
                              Icon(
                                Icons.location_pin,
                                color: Colors.yellow.shade600,
                                size: screenHeight * 0.034,
                              ),
                              Padding(
                                padding: const EdgeInsets.only(left: 5.0),
                                child: Text(
                                  governorate,
                                  style: TextStyle(
                                    fontSize: screenHeight * 0.028,
                                    color: Colors.black,
                                  ),
                                ),
                              )
                            ]),
                          ),
                          // description title
                          Positioned(
                              top: screenHeight * 0.18,
                              child: Text(
                                "Description",
                                style: TextStyle(
                                    fontSize: screenHeight * 0.03,
                                    fontWeight: FontWeight.w700),
                              )),
                          // descrition body
                          Positioned(
                              top: screenHeight * 0.23,
                              child: Container(
                                height: screenHeight * 0.21,
                                width: screenWidth * 0.9,
                                child: Text(
                                  description + "....",
                                  style: TextStyle(
                                    fontSize: screenHeight * 0.02,
                                  ),
                                ),
                              )),
                          // read full description
                          Positioned(
                              top: screenHeight * 0.43,
                              child: InkWell(
                                  onTap: () {
                                    Navigator.of(context).push(
                                        MaterialPageRoute(
                                            builder: (context) =>
                                                FullDescriptionScreen(
                                                  fullDescriptionString:
                                                      siteData["description"],
                                                )));
                                  },
                                  child: Row(
                                    children: [
                                      Text(
                                        "Read full Description",
                                        style: TextStyle(
                                          fontSize: screenHeight * 0.023,
                                          fontWeight: FontWeight.bold,
                                          decoration: TextDecoration.underline,
                                        ),
                                      ),
                                      Icon(
                                        Icons.arrow_forward_ios,
                                        color: Colors.black,
                                        size: screenHeight * 0.021,
                                      ),
                                    ],
                                  ))),
                          // tages
                          Positioned(
                              top: screenHeight * 0.48,
                              child: Row(
                                children: [
                                  ...List.generate(
                                      2,
                                      (index) => Container(
                                            margin: EdgeInsets.only(right: 18),
                                            padding: EdgeInsets.only(
                                                left: 15, right: 15),
                                            height: screenHeight * 0.06,
                                            decoration: BoxDecoration(
                                              color: Color(0xffffd600),
                                              borderRadius: BorderRadius.all(
                                                  Radius.circular(
                                                      screenHeight * 0.07)),
                                            ),
                                            child: Center(
                                                child: index == 0
                                                    ? Text(location)
                                                    : Text(type)),
                                          ))
                                ],
                              )),
                          // Divider
                          Positioned(
                              top: screenHeight * 0.57,
                              child: Container(
                                height: 1,
                                width: screenWidth * 0.9,
                                color: Color(0x99c2c2c2),
                              )),
                          // history
                          Positioned(
                            top: screenHeight * 0.59,
                            child: Row(children: [
                              Container(
                                width: screenHeight * 0.038,
                                height: screenHeight * 0.038,
                                child:
                                    Image.asset("assets/icons/historyData.png"),
                              ),
                              Padding(
                                padding: const EdgeInsets.only(left: 5.0),
                                child: Text(
                                  "History",
                                  style: TextStyle(
                                    fontSize: screenHeight * 0.028,
                                    color: Colors.black,
                                  ),
                                ),
                              )
                            ]),
                          ),
                          Positioned(
                            top: screenHeight * 0.64,
                            child: Padding(
                              padding: const EdgeInsets.only(left: 5.0),
                              child: Row(
                                mainAxisAlignment: MainAxisAlignment.start,
                                crossAxisAlignment: CrossAxisAlignment.center,
                                children: [
                                  Text(
                                    "Era: ",
                                    style: TextStyle(
                                        color: Color(0xffffd600),
                                        fontWeight: FontWeight.bold,
                                        fontSize: screenHeight * 0.023),
                                  ),
                                  ...List.generate(3, (index) {
                                    return era[index] == 0
                                        ? Container()
                                        : Text(
                                            " ${era[index]} ",
                                            style: TextStyle(
                                                color: Color(0xff6a6a6a),
                                                fontWeight: FontWeight.bold,
                                                fontSize: screenHeight * 0.023),
                                          );
                                  })
                                ],
                              ),
                            ),
                          ),
                          Positioned(
                            top: screenHeight * 0.67,
                            child: Padding(
                              padding: const EdgeInsets.only(left: 5.0),
                              child: Row(
                                mainAxisAlignment: MainAxisAlignment.start,
                                crossAxisAlignment: CrossAxisAlignment.center,
                                children: [
                                  Text(
                                    "Famous Figure: ",
                                    style: TextStyle(
                                        color: Color(0xffffd600),
                                        fontWeight: FontWeight.bold,
                                        fontSize: screenHeight * 0.023),
                                  ),
                                  Text(
                                    " $famousFigure ",
                                    style: TextStyle(
                                        color: Color(0xff6a6a6a),
                                        fontWeight: FontWeight.bold,
                                        fontSize: screenHeight * 0.023),
                                  )
                                ],
                              ),
                            ),
                          ),
                          // Divider
                          Positioned(
                              top: screenHeight * 0.73,
                              child: Container(
                                height: 1,
                                width: screenWidth * 0.9,
                                color: Color(0x99c2c2c2),
                              )),
                          // location
                          Positioned(
                            top: screenHeight * 0.75,
                            child: Row(children: [
                              Icon(
                                Icons.location_pin,
                                color: Colors.black,
                                size: screenHeight * 0.034,
                              ),
                              Padding(
                                padding: const EdgeInsets.only(left: 5.0),
                                child: Text(
                                  "Location",
                                  style: TextStyle(
                                    fontSize: screenHeight * 0.028,
                                    color: Colors.black,
                                  ),
                                ),
                              )
                            ]),
                          ),
                          Positioned(
                              top: screenHeight * 0.79,
                              child: Padding(
                                padding: const EdgeInsets.only(left: 12),
                                child: Text(
                                  location,
                                  style: TextStyle(
                                    fontSize: screenHeight * 0.028,
                                    color: Color(0xaa6a6a6a),
                                  ),
                                ),
                              )),
                          // Divider
                          Positioned(
                              top: screenHeight * 0.847,
                              child: Container(
                                height: 1,
                                width: screenWidth * 0.9,
                                color: Color(0x99c2c2c2),
                              )),
                          // opening hours
                          Positioned(
                            top: screenHeight * 0.87,
                            child: Row(children: [
                              Icon(
                                Icons.schedule,
                                color: Colors.black,
                                size: screenHeight * 0.034,
                              ),
                              Padding(
                                padding: const EdgeInsets.only(left: 5.0),
                                child: Text(
                                  "Opening Hours",
                                  style: TextStyle(
                                    fontSize: screenHeight * 0.028,
                                    color: Colors.black,
                                  ),
                                ),
                              )
                            ]),
                          ),
                          Positioned(
                              top: screenHeight * 0.91,
                              child: Padding(
                                padding: const EdgeInsets.only(left: 12),
                                child: Text(
                                  "Everyday from ${openingHours[0]}:00 to ${openingHours[1]}:00",
                                  style: TextStyle(
                                    fontSize: screenHeight * 0.023,
                                    color: Color(0xaa6a6a6a),
                                  ),
                                ),
                              )),
                          // Divider
                          Positioned(
                              top: screenHeight * 0.96,
                              child: Container(
                                height: 1,
                                width: screenWidth * 0.9,
                                color: Color(0x99c2c2c2),
                              )),
                          // prices
                          Positioned(
                            top: screenHeight * 0.98,
                            child: Row(children: [
                              Icon(
                                Icons.monetization_on_outlined,
                                color: Color(0xffffd600),
                                size: screenHeight * 0.034,
                              ),
                              Padding(
                                padding: const EdgeInsets.only(left: 5.0),
                                child: Text(
                                  "Prices",
                                  style: TextStyle(
                                    fontSize: screenHeight * 0.028,
                                    fontWeight: FontWeight.bold,
                                    color: Colors.black,
                                  ),
                                ),
                              )
                            ]),
                          ),
                          Positioned(
                            top: screenHeight * 1.025,
                            child: Padding(
                              padding: const EdgeInsets.only(left: 5.0),
                              child: Row(
                                mainAxisAlignment: MainAxisAlignment.start,
                                crossAxisAlignment: CrossAxisAlignment.center,
                                children: [
                                  Text(
                                    "Egyption Adult: ",
                                    style: TextStyle(
                                        color: Colors.black,
                                        fontWeight: FontWeight.bold,
                                        fontSize: screenHeight * 0.023),
                                  ),
                                  Text(
                                    " ${prices[0]} EGP ",
                                    style: TextStyle(
                                        color: Color(0xffffd600),
                                        fontWeight: FontWeight.bold,
                                        fontSize: screenHeight * 0.02),
                                  ),
                                  Text(
                                    "/ Ticket",
                                    style: TextStyle(
                                        color: Colors.black,
                                        fontWeight: FontWeight.bold,
                                        fontSize: screenHeight * 0.02),
                                  ),
                                ],
                              ),
                            ),
                          ),
                          Positioned(
                            top: screenHeight * 1.055,
                            child: Padding(
                              padding: const EdgeInsets.only(left: 5.0),
                              child: Row(
                                mainAxisAlignment: MainAxisAlignment.start,
                                crossAxisAlignment: CrossAxisAlignment.center,
                                children: [
                                  Text(
                                    "Egyption Student: ",
                                    style: TextStyle(
                                        color: Colors.black,
                                        fontWeight: FontWeight.bold,
                                        fontSize: screenHeight * 0.023),
                                  ),
                                  Text(
                                    " ${prices[1]} EGP ",
                                    style: TextStyle(
                                        color: Color(0xffffd600),
                                        fontWeight: FontWeight.bold,
                                        fontSize: screenHeight * 0.02),
                                  ),
                                  Text(
                                    "/ Ticket",
                                    style: TextStyle(
                                        color: Colors.black,
                                        fontWeight: FontWeight.bold,
                                        fontSize: screenHeight * 0.02),
                                  ),
                                ],
                              ),
                            ),
                          ),
                          Positioned(
                            top: screenHeight * 1.085,
                            child: Padding(
                              padding: const EdgeInsets.only(left: 5.0),
                              child: Row(
                                mainAxisAlignment: MainAxisAlignment.start,
                                crossAxisAlignment: CrossAxisAlignment.center,
                                children: [
                                  Text(
                                    "Foreigner Adult: ",
                                    style: TextStyle(
                                        color: Colors.black,
                                        fontWeight: FontWeight.bold,
                                        fontSize: screenHeight * 0.023),
                                  ),
                                  Text(
                                    " ${prices[2]} EGP ",
                                    style: TextStyle(
                                        color: Color(0xffffd600),
                                        fontWeight: FontWeight.bold,
                                        fontSize: screenHeight * 0.02),
                                  ),
                                  Text(
                                    "/ Ticket",
                                    style: TextStyle(
                                        color: Colors.black,
                                        fontWeight: FontWeight.bold,
                                        fontSize: screenHeight * 0.02),
                                  ),
                                ],
                              ),
                            ),
                          ),
                          Positioned(
                            top: screenHeight * 1.11,
                            child: Padding(
                              padding: const EdgeInsets.only(left: 5.0),
                              child: Row(
                                mainAxisAlignment: MainAxisAlignment.start,
                                crossAxisAlignment: CrossAxisAlignment.center,
                                children: [
                                  Text(
                                    "Foreigner Student: ",
                                    style: TextStyle(
                                        color: Colors.black,
                                        fontWeight: FontWeight.bold,
                                        fontSize: screenHeight * 0.023),
                                  ),
                                  Text(
                                    " ${prices[3]} EGP ",
                                    style: TextStyle(
                                        color: Color(0xffffd600),
                                        fontWeight: FontWeight.bold,
                                        fontSize: screenHeight * 0.02),
                                  ),
                                  Text(
                                    "/ Ticket",
                                    style: TextStyle(
                                        color: Colors.black,
                                        fontWeight: FontWeight.bold,
                                        fontSize: screenHeight * 0.02),
                                  ),
                                ],
                              ),
                            ),
                          ),
                        ]),
                      )),
                    ),
                  ),
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
