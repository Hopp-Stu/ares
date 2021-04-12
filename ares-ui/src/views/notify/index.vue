/*******************************************************************************
 * Copyright (c) 2021 - 9999, ARES
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

<template>
  <div class="app-container">
    <el-card v-show="msgList.length == 0" shadow="always">
      <el-alert title="没有新的公告信息" type="info" center show-icon>
      </el-alert>
    </el-card>

    <el-timeline>
      <el-timeline-item
        v-for="msg in msgList"
        :timestamp="parseTime(msg.createTime, '{y}-{m}-{d}')"
        :key="msg.id"
        :icon="msg.noticeType == 1 ? 'el-icon-position' : 'el-icon-message'"
        size="large"
        :type="msg.noticeType == 1 ? 'primary' : 'warning'"
        placement="top"
      >
        <el-card shadow="always">
          <div slot="header" class="clearfix">
            <span>{{ msg.noticeTitle }}</span>
            <span style="float: right; padding: 3px 0"
              >{{ msg.creator }} - {{ msg.createTime }}</span
            >
          </div>
          <p v-html="msg.noticeContent" />
        </el-card>
      </el-timeline-item>
    </el-timeline>
  </div>
</template>

<script>
import { getNotices } from "@/api/notify/message";
import store from "@/store";

export default {
  name: "Message",
  data() {
    return {
      msgList: [],
    };
  },
  created() {
    this.getList();
    store.dispatch("GetNoticeNumber");
  },
  methods: {
    getList() {
      getNotices().then((response) => {
        this.msgList = response.data;
      });
    },
  },
};
</script>
