#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_live_ditto_myapplication_MainActivity_stringFromJNI(
        JNIEnv* env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}

extern "C" JNIEXPORT void JNICALL
Java_live_ditto_myapplication_MainActivity_sendData(
        JNIEnv* env,
        jobject,
        jbyte color,
        jbyteArray data
) {
    jboolean isCopy;
    jsize len = env->GetArrayLength(data);
    jbyte* cData = env->GetByteArrayElements(data, &isCopy);

    for(int i = 0; i < len; i++) {
        cData[i] = color;
    }

    env->ReleaseByteArrayElements(data, cData, JNI_COMMIT);
}