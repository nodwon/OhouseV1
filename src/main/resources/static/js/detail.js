import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter);

// 컴포넌트를 import하여 사용할 수 있습니다.
import Home from './components/Home.vue';
import PostDetail from './components/PostDetail.vue';

// 라우트 정의
const routes = [
    { path: '/', component: Home },
    { path: '/posts/:id', component: PostDetail }
];

const router = new VueRouter({
    routes
});

new Vue({
    el: '#app',
    router, // 라우터를 Vue 인스턴스에 등록
    data: {
        postData: {
            title: '',
            content: ''
        }
    },
    methods: {
        savePost() {
            axios.post('/posts', this.postData)
                .then((response) => {
                    if (response.status === 201) {
                        // 게시물 저장 성공 시 메인 페이지로 이동
                        this.$router.push('/');
                    }
                })
                .catch((error) => {
                    console.error(error);
                });
        },
        cancelPost() {
            // 데이터 초기화 로직
        }
    }
});
