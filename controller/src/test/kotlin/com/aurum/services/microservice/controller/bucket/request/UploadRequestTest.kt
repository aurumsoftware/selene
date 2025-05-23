package com.aurum.services.microservice.controller.bucket.request

import com.aurum.services.document.controller.bucket.exception.UploadRequestException
import com.aurum.services.document.domain.bucket.enumxs.StorageBucketType
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.NullAndEmptySource
import org.junit.jupiter.params.provider.ValueSource

internal class UploadRequestTest {

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = ["   "])
    fun `deve lançar exceção se mimeType for nulo ou vazio`(mimeType : String?) {
        val uploadRequest = UploadRequest(mimeType, "filename.pdf", StorageBucketType.DOCUMENT)
        val exception = assertThrows(UploadRequestException::class.java) { uploadRequest.getUpload() }
        assertEquals(UploadRequestException.EMPTY_MIMETYPE.message, exception.message)
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = ["   "])
    fun `deve lançar exceção se fileName for nulo ou vazio`(filename : String?) {
        val uploadRequest = UploadRequest("application/pdf", filename, StorageBucketType.DOCUMENT)
        val exception = assertThrows(UploadRequestException::class.java) { uploadRequest.getUpload() }
        assertEquals(UploadRequestException.EMPTY_FILENAME.message, exception.message)
    }

}