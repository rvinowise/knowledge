import os
import lang_style.core.lang_style.synonim_finder.api_webster as sf

def check_all():
    res = sf.get_synonims_for_word("rain", "NOUN")
    print(res)
    print(
        """cwd={0}
path={1}
        """.format(
            os.getcwd(),
            os.path.realpath(__file__)
            )
    )
    
if __name__ == "__main__":
    check_all()