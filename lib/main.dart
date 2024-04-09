import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
          appBar: AppBar(
            backgroundColor: Colors.white,
            leading: Icon(
              Icons.refresh,
              color: Colors.black,
              size: 50,
              weight: 100,
            ),
            actions:
            [
              ElevatedButton(
                  style: ButtonStyle(
                    backgroundColor: MaterialStateProperty.all<Color>(Colors.white),
                      padding: MaterialStateProperty.all<EdgeInsets>(EdgeInsets.all(10)),
                  ),
                  onPressed: (){print("test");},
                  child: Text("친구코드", style: TextStyle(color: Colors.black),)
              )
            ],
          ),
          body: Container(
            width: double.infinity,
            child: Column(
              children: [
                Flexible(flex:5, child: Container(
                  margin: EdgeInsets.fromLTRB(0,10,0,0),
                    width: double.infinity,
                    decoration: BoxDecoration(
                        border: Border(
                          bottom: BorderSide(
                            width: 5
                          ),
                        )
                    ),
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                    children: [
                      Image.asset("images.jpg"),
                      Text("이생존"),
                      Text("날짜"),
                      Text("방금 전")
                    ],
                  )
                )),
                Flexible(flex:5, child: Container(
                  margin: EdgeInsets.fromLTRB(10,10,10,0),
                  child: Column(

                    children: [
                      Row(
                        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                        children: [
                          Image.asset("images.jpg", width: 70,),
                          Text("홍길동"),
                          Column(
                            children: [
                              Text("12시간 전"),
                              Text("2024/04/25")
                            ],
                          ),
                          ElevatedButton(onPressed: null, child: Text("요청"))
                        ],

                      )
                    ],
                  ),

                )),

              ],
            ),
          ),
        ));
  }
}
