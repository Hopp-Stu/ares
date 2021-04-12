<template>
  <div>
    <header style="padding: 80px 15px 48px 50px">
      <div class="explain">
        <h1>My Web</h1>
        <h4>This is for learn</h4>
      </div>
    </header>
    <div class="index-wrapper">
      <ul>
        <li
          class="blog-wrapper"
          v-for="item of contents"
          :key="item.id"
          @click="$parent.location('/details')"
        >
          <p class="blog-time">{{ item.time }}</p>
          <h3 class="blog-title">{{ item.title }}</h3>
          <div class="blog-content">
            {{ item.content }}
            <span class="blog-more">阅读原文...</span>
          </div>
          <div class="blog-tag">
            <ul>
              <li v-for="tag of item.tags" :key="tag.id">{{ tag.name }}</li>
            </ul>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "index",
  data() {
    return {
      contents: [
        {
          id: "1",
          time: "2021年4月12日",
          title: "我的主页",
          content: "这是我的主页",
          tags: [
            { id: "1", name: "标签" },
            { id: "2", name: "标签" },
            { id: "3", name: "标签" },
          ],
        },
        {
          id: "2",
          time: "2021年4月12日",
          title: "我的主页",
          content: "这是我的主页",
          tags: [
            { id: "1", name: "标签" },
            { id: "2", name: "标签" },
            { id: "3", name: "标签" },
          ],
        },
        {
          id: "3",
          time: "2021年4月12日",
          title: "我的主页",
          content: "这是我的主页",
          tags: [
            { id: "1", name: "标签" },
            { id: "2", name: "标签" },
            { id: "3", name: "标签" },
          ],
        },
      ],
    };
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.index-wrapper {
  max-width: 960px;
  margin: 30px auto 40px;
}
.blog-wrapper {
  margin-bottom: 30px;
  padding: 12px 12px 0;
  background: #fff;
  border-radius: 3px;
  box-shadow: 0 1px 2px rgba(151, 151, 151, 0.58);
}
.blog-time {
  line-height: 24px;
  margin: 0 0 10px;
  font-size: 13px;
  font-weight: bold;
  color: #727272;
  overflow: hidden;
}
.blog-title {
  margin-bottom: 15px;
  font-size: 24px;
  line-height: 32px;
  color: #3f51b5;
}
.blog-content {
  word-break: break-all;
  padding-bottom: 20px;
  line-height: 1.8;
}
.blog-more {
  display: inline-block;
  padding: 0 6px;
  font-weight: 500;
  color: #3f51b5 !important;
  border: none !important;
  border-radius: 3px;
}
.blog-tag {
  position: relative;
  margin: 0 -12px;
  padding: 12px 20px 8px;
  border-top: 1px solid #ddd;
}
.blog-tag li {
  display: inline-block;
  margin: 0 8px 8px 0;
  border-radius: 2px;
  background: #8bc34a;
  padding: 0 16px;
  line-height: 28px;
  color: rgba(255, 255, 255, 0.8);
}
</style>
