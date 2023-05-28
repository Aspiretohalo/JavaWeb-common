export default {
    selectItems(state) {
        // if (state.Attribute === 1)
        // 按照点击的tab传过来的Attribute值，过滤出需要展示的items
        console.log('state.Attribute:' + state.Attribute);
        // console.log(state.listItem.num);
        if (state.Attribute === 1) return state.listItem
        return state.listItem.filter(item => item.num == state.Attribute)
    },
}