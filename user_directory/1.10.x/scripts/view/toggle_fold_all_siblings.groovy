def all_siblings = node.parent.children.clone()

all_siblings.each {
	it.folded = !was_folded;
}