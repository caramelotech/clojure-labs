import { defineConfig } from 'astro/config';
import starlight from '@astrojs/starlight';

export default defineConfig({
  site: 'https://caramelotech.github.io',
  base: '/clojure-labs',
  integrations: [
    starlight({
      title: 'Clojure Labs',
      customCss: ['./src/styles/custom.css'],
      social: [
        {
          icon: 'github',
          label: 'GitHub',
          href: 'https://github.com/caramelotech/clojure-labs',
        },
      ],
      defaultLocale: 'root',
      locales: {
        root: { label: 'Portugues', lang: 'pt-BR' },
      },
    }),
  ],
});
