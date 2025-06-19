export function XssTest(msg){
        const xss = () => {
                return '<div>'+msg+'</div>';
        }
        return xss;
}