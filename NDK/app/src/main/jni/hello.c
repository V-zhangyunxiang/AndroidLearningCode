#include "com_unibike_ndk_MainActivity.h"
JNIEXPORT jint JNICALL Java_com_unibike_ndk_MainActivity_add
  (JNIEnv * env, jobject obj, jint num1, jint num2){
          return num1+num2;
  }