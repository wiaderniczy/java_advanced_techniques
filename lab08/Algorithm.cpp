#include "Algorithm.h"

JNIEXPORT void JNICALL Java_Algorithm_solveNative
(JNIEnv * env, jobject thisObject, jintArray coreI, jintArray processingI){
    jsize coreL = env->GetArrayLength(coreI);
    jsize procL = env->GetArrayLength(processingI);

    jsize coreSize = static_cast<>(sqrt(coreL));
    jsize procSize = static_cast<>(sqrt(procL));

    jint* core = env->GetIntArrayElements(coreI, nullptr);
    jint* processing = env->GetIntArrayElements(processingI, nullptr);

    jsize resultSize = procSize - coreSize + 1;
    std::cout<<resultSize;
    jint* result = new jint[resultSize * resultSize];

    for (int i = 0; i < resultSize; ++i) {
        for (int j = 0; j < resultSize; ++j) {
            int sum = 0;
            for (int k = 0; k < coreSize; ++k) {
                for (int l = 0; l < coreSize; ++l) {
                    int rowIndex = i + k - coreSize / 2;
                    int colIndex = j + l - coreSize / 2;
                    if (rowIndex >= 0 && rowIndex < coreSize && colIndex >= 0 && colIndex < coreSize){
                        sum += core[k * coreSize + l] * processing[rowIndex * coreSize + colIndex];
                    }

                }
            }
            result[i * resultSize + j] = sum;
        }
    }

    for (jint i = 0; i < resultSize; ++i) {
        for (jint j = 0; j < resultSize; ++j) {
            std::cout << result[i * resultSize + j] << " ";
        }
        std::cout << std::endl;
    }

    delete[] result;

    env->ReleaseIntArrayElements(coreI, core, JNI_ABORT);
    env->ReleaseIntArrayElements(processingI, processing, JNI_ABORT);
}