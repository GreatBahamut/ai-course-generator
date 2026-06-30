package com.aicoursegenerator.integration.ai;

import java.util.List;

record ClaudeMessageResponse(List<ContentBlock> content) {

    record ContentBlock(String type, String text) {
    }
}
