def all_siblings = node.parent.children.clone()
def was_folded = node.folded;
all_siblings.each {
	it.folded = !was_folded;
}