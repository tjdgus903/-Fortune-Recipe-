package org.example.com.fortune.app.domain.common.exception

class BusinessException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)